package agh.ics.oop;

import agh.ics.oop.model.Genomes.GenomesFactory;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Map.IceMap;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationConfig;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApp extends Application {
//    private final SimulationConfig simulationConfig;
//    private final GenomesFactory genomesFactory;
//    private final WorldMap worldMap;
//    private final boolean saveToFile;
//    public SimulationApp(SimulationConfig simulationConfig, GenomesFactory genomesFactory,
//                         WorldMap worldMap, boolean saveToFile) {
//        this.simulationConfig = simulationConfig;
//        this.genomesFactory = genomesFactory;
//        this.worldMap = worldMap;
//        this.saveToFile = saveToFile;
//    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane rootNode = loader.load();
                var conf = new SimulationConfig(100, 100, "", 40, 20, 30
                , "", 100, 100, 100, 50, 1,
                        4, "", 8, "");
        var simulation = new Simulation(new EarthMap(100, 100), conf, new NormalGenomesFactory(), true);
        SimulationPresenter controller = loader.getController();
//        Simulation simulation = new Simulation(worldMap, simulationConfig, genomesFactory, saveToFile);
        simulation.addListener(controller);
        controller.setSimulation(simulation);

        configureStage(primaryStage, rootNode);
        primaryStage.show();
        primaryStage.setOnCloseRequest((event -> simulation.stop()));

    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
