package agh.ics.oop.presenter;

import agh.ics.oop.model.Genomes.GenomesAbstract;
import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Genomes.SwapGenomesFactory;
import agh.ics.oop.model.Map.AbstractMap;
import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Map.IceMap;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ConfigPresenter {
    @FXML
    public Spinner<Integer> mapWidth;
    @FXML
    public Spinner<Integer> mapHeight;
    @FXML
    public ComboBox<String> mapVariant;
    @FXML
    public Spinner<Integer> startingPlantCount;
    @FXML
    public Spinner<Integer> plantEnergy;
    @FXML
    public Spinner<Integer> plantsPerDay;
    @FXML
    public ComboBox<String> plantGrowthVariant;
    @FXML
    public Spinner<Integer> startingAnimalCount;
    @FXML
    public Spinner<Integer> startingAnimalEnergy;
    @FXML
    public Spinner<Integer> energyToBeFull;
    @FXML
    public Spinner<Integer> energyToReproduce;
    @FXML
    public Spinner<Integer> genomeLength;
    @FXML
    public Spinner<Integer> minMutation;
    @FXML
    public Spinner<Integer> maxMutation;
    @FXML
    public ComboBox<String> mutationVariant;
    @FXML
    public ComboBox<String> animalBehaviourVariant;
    @FXML
    public Spinner<Integer> dayLength;
    @FXML
    public CheckBox exportStats;
    @FXML
    public Label errorMessage;
    @FXML
    public void initialize() {
        String [] mapTypes = {"Earth", "Poles"};
        mapVariant.getItems().addAll(mapTypes);
        String [] plantGrowthTypes = {"Forested equator"};
        plantGrowthVariant.getItems().addAll(plantGrowthTypes);
        String [] mutationTypes = {"Random", "Swap"};
        mutationVariant.getItems().addAll(mutationTypes);
        String [] animalBehaviourTypes = {"Full predestination"};
        animalBehaviourVariant.getItems().addAll(animalBehaviourTypes);
    }

    public void onStartClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane rootNode = loader.load();
        Stage primaryStage = new Stage();
        if (verifyConfig()) {
            updateErrorMessage("");
            Simulation simulation = getSimulation();
            SimulationPresenter controller = loader.getController();
            simulation.addListener(controller);
            controller.setSimulation(simulation);
            configureStage(primaryStage, rootNode);
            primaryStage.show();
        }
    }

    private Simulation getSimulation() {
        var conf = new SimulationConfig(mapWidth.getValue(), mapHeight.getValue(), mapVariant.getValue(),
                startingPlantCount.getValue(), plantEnergy.getValue(), plantsPerDay.getValue(),
                plantGrowthVariant.getValue(), startingAnimalCount.getValue(), startingAnimalEnergy.getValue(),
                energyToBeFull.getValue(), energyToReproduce.getValue(), minMutation.getValue(),
                maxMutation.getValue(), mutationVariant.getValue(), genomeLength.getValue(),
                animalBehaviourVariant.getValue());
        WorldMap chosenMap = null;
        GenomesFactory chosenMutationVariant = null;
        switch (mapVariant.getValue()) {
            case "Earth":
                chosenMap = new EarthMap(mapWidth.getValue(), mapHeight.getValue());
                break;
            case "Poles":
                chosenMap = new IceMap(mapWidth.getValue(), mapHeight.getValue());
                break;
        }
        switch (mutationVariant.getValue()) {
            case "Random":
                chosenMutationVariant = new NormalGenomesFactory();
                break;
            case "Swap":
                chosenMutationVariant = new SwapGenomesFactory();
                break;
        }
        return new Simulation(chosenMap, conf, chosenMutationVariant, exportStats.isSelected());
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
    private Boolean verifyConfig() {
        if (mapVariant.getValue() == null) {
            updateErrorMessage("Choose a map variant!");
            return false;
        }
        if (startingPlantCount.getValue() > mapWidth.getValue() * mapHeight.getValue()) {
            updateErrorMessage("Starting plant count is greater than map area!");
        }
        if (plantGrowthVariant.getValue() == null) {
            updateErrorMessage("Choose a plant growth variant!");
            return false;
        }
        if (startingAnimalEnergy.getValue() > energyToBeFull.getValue()) {
            updateErrorMessage("Starting animal energy exceeds maximum energy!");
            return false;
        }
        if (energyToReproduce.getValue() > energyToBeFull.getValue()) {
            updateErrorMessage("Energy needed for reproduction exceeds maximum energy!");
            return false;
        }
        if (minMutation.getValue() > maxMutation.getValue()) {
            updateErrorMessage("Minimum number of mutations exceeds maximum number of mutations!");
        }
        if (mutationVariant.getValue() == null) {
            updateErrorMessage("Choose a mutation variant!");
            return false;
        }
        if (animalBehaviourVariant.getValue() == null) {
            updateErrorMessage("Choose an animal behaviour variant!");
            return false;
        }
        return true;
    }
    public void onSaveClicked(ActionEvent actionEvent) {

    }
    public void onLoadClicked(ActionEvent actionEvent) {

    }
    private void updateErrorMessage(String message) {
        errorMessage.setText(message);
    }
}
