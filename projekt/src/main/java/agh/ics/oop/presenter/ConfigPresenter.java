package agh.ics.oop.presenter;

import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Genomes.SwapGenomesFactory;
import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Map.IceMap;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.model.Utilities.ConfigLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigPresenter {
    @FXML
    public Spinner<Integer> mapWidth, mapHeight, startingPlantCount, plantEnergy, plantsPerDay,
            startingAnimalCount, startingAnimalEnergy, energyToBeFull, energyToReproduce, genomeLength, minMutation,
            maxMutation, dayLength;
    @FXML
    public ComboBox<String> mapVariant, plantGrowthVariant, mutationVariant, animalBehaviourVariant;
    @FXML
    public CheckBox exportStats;
    @FXML
    public Label messageLabel;
    @FXML
    public void initialize() {
        String [] mapTypes = {"Earth", "Poles"};
        mapVariant.getItems().addAll(mapTypes);
        mapVariant.setValue("Earth");
        String [] plantGrowthTypes = {"Forested equator"};
        plantGrowthVariant.getItems().addAll(plantGrowthTypes);
        plantGrowthVariant.setValue("Forested equator");
        String [] mutationTypes = {"Random", "Swap"};
        mutationVariant.getItems().addAll(mutationTypes);
        mutationVariant.setValue("Random");
        String [] animalBehaviourTypes = {"Full predestination"};
        animalBehaviourVariant.getItems().addAll(animalBehaviourTypes);
        animalBehaviourVariant.setValue("Full predestination");
    }

    public void onStartClicked() throws IOException {
        if (verifyConfig()) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane rootNode = loader.load();
            Stage primaryStage = new Stage();
            setErrorMessage("");
            Simulation simulation = getSimulation();
            SimulationPresenter controller = loader.getController();
            simulation.addListener(controller);
            controller.setSimulation(simulation);
            configureStage(primaryStage, rootNode, "Darwin World");
            primaryStage.show();
            primaryStage.setOnCloseRequest((event -> simulation.stop()));
        }
    }

    private Simulation getSimulation() {
        var conf = new SimulationConfig(mapWidth.getValue(), mapHeight.getValue(), mapVariant.getValue(),
                startingPlantCount.getValue(), plantEnergy.getValue(), plantsPerDay.getValue(),
                plantGrowthVariant.getValue(), startingAnimalCount.getValue(), startingAnimalEnergy.getValue(),
                energyToBeFull.getValue(), energyToReproduce.getValue(), minMutation.getValue(),
                maxMutation.getValue(), mutationVariant.getValue(), genomeLength.getValue(),
                animalBehaviourVariant.getValue(), dayLength.getValue());
        WorldMap chosenMap = null;
        GenomesFactory chosenMutationVariant = null;
        chosenMap = switch (mapVariant.getValue()) {
            case "Earth" -> new EarthMap(mapWidth.getValue(), mapHeight.getValue());
            case "Poles" -> new IceMap(mapWidth.getValue(), mapHeight.getValue());
            default -> chosenMap;
        };
        chosenMutationVariant = switch (mutationVariant.getValue()) {
            case "Random" -> new NormalGenomesFactory();
            case "Swap" -> new SwapGenomesFactory();
            default -> chosenMutationVariant;
        };
        return new Simulation(chosenMap, conf, chosenMutationVariant, exportStats.isSelected());
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot, String name) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
    private Boolean verifyConfig() {
        if (mapVariant.getValue() == null) {
            setErrorMessage("Choose a map variant!");
            return false;
        }
        if (startingPlantCount.getValue() > mapWidth.getValue() * mapHeight.getValue()) {
            setErrorMessage("Starting plant count is greater than map area!");
            return false;
        }
        if (plantGrowthVariant.getValue() == null) {
            setErrorMessage("Choose a plant growth variant!");
            return false;
        }
        if (startingAnimalEnergy.getValue() > energyToBeFull.getValue()) {
            setErrorMessage("Starting animal energy exceeds maximum energy!");
            return false;
        }
        if (energyToReproduce.getValue() > energyToBeFull.getValue()) {
            setErrorMessage("Energy needed for reproduction exceeds maximum energy!");
            return false;
        }
        if (minMutation.getValue() > maxMutation.getValue()) {
            setErrorMessage("Minimum number of mutations exceeds maximum number of mutations!");
            return false;
        }
        if (mutationVariant.getValue() == null) {
            setErrorMessage("Choose a mutation variant!");
            return false;
        }
        if (animalBehaviourVariant.getValue() == null) {
            setErrorMessage("Choose an animal behaviour variant!");
            return false;
        }
        return true;
    }
    public void onSaveClicked() throws IOException {
        if (verifyConfig()) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("saveconfig.fxml"));
            BorderPane rootNode = loader.load();
            Stage primaryStage = new Stage();
            setErrorMessage("");
            SaveConfigPresenter controller = loader.getController();
            controller.setPresenter(this);
            configureStage(primaryStage, rootNode, "Save Configuration");
            primaryStage.show();
        }
    }
    public void onLoadClicked() {
        ConfigLoader configLoader = new ConfigLoader(this);
        Stage primaryStage = new Stage();
        Path path = Paths.get("");
        File directory = new File(path.toAbsolutePath() + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "configs");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(directory);
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            if (configLoader.set(selectedFile)) {
                if (configLoader.load()) {
                    setSuccessMessage("Configuration file loaded!");
                }
                else {
                    setErrorMessage("Could not load configuration file!");
                }
            }
            else {
                setErrorMessage("Incorrect file extension!");
            }
        }
    }
    private void setErrorMessage(String message) {
        messageLabel.setTextFill(Paint.valueOf("red"));
        messageLabel.setText(message);
    }
    private void setSuccessMessage(String message) {
        messageLabel.setTextFill(Paint.valueOf("green"));
        messageLabel.setText(message);
    }
}
