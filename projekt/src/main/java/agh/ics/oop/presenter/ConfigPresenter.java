package agh.ics.oop.presenter;

import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigPresenter {
    public void onStartClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane rootNode = loader.load();
        Stage primaryStage = new Stage();
        var conf = new SimulationConfig(5, 5, "", 4, 3, 2
                , "", 5, 3, 20, 5, 1, 4, 0, 8, "");
        var simulation = new Simulation(new EarthMap(5, 5), conf, new NormalGenomesFactory(), true);
        SimulationPresenter controller = loader.getController();
//        Simulation simulation = new Simulation(worldMap, simulationConfig, genomesFactory, saveToFile);
        simulation.addListener(controller);
        controller.setSimulation(simulation);

        configureStage(primaryStage, rootNode);
        primaryStage.show();



    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
