package agh.ics.oop.presenter;

import agh.ics.oop.SimulationApp;
import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationListener;
import agh.ics.oop.model.Stats.SimulationStatsRecord;
import agh.ics.oop.model.WorldElements.Vector2d;
import agh.ics.oop.model.WorldElements.WorldElement;
import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Plant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.List;

public class SimulationPresenter implements SimulationListener {
    public GridPane mapGrid;
    public Label statisticsLabel;
    public Label animalInfo;
    private Simulation simulation;
    private Animal selectedAnimal;
    public void drawMap(){
        var worldMap = simulation.getWorldMap();
        clearGrid();
        int size = 50;
        mapGrid.getColumnConstraints().add(new ColumnConstraints(size));
        mapGrid.getRowConstraints().add(new RowConstraints(size));
        var bounds = worldMap.getCurrentBounds();
        Label label1 = new Label("y/x");
        GridPane.setHalignment(label1, HPos.CENTER);
        mapGrid.add(label1,0,0);
        int row = 1;
        int yMax = bounds.upperRight().getY();
        int yMin = bounds.downLeft().getY();
        for (int i = yMax; i >= yMin; i--){
            Label label = new Label("%d".formatted(i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(size));
            mapGrid.add(label,0,row);
            row++;
        }
        int column = 1;
        int xMin = bounds.downLeft().getX();
        int xMax = bounds.upperRight().getX();
        for (int i = xMin; i <= xMax; i++){
            Label label = new Label("%d".formatted(i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(size));
            mapGrid.add(label,column,0);
            column++;
        }
        for (int i = xMin; i <= xMax; i++){
            for (int j = yMax; j >= yMin; j--){
                Vector2d p  = new Vector2d(i, j);
                int finalJ = j;
                int finalI = i;
                worldMap.objectAt(p).ifPresentOrElse((WorldElement el) -> {
                    if (el instanceof Animal) {
                        Circle shape = getCircle((Animal) el, size);
                        shape.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleCellClick((Animal) el));
                        GridPane.setHalignment(shape, HPos.CENTER);
                        mapGrid.add(shape, finalI - xMin + 1, yMax - finalJ + 1);

                    } else if (el instanceof Plant) {
                        Rectangle plantSquare = new Rectangle(size, size, Color.GREEN);
                        GridPane.setHalignment(plantSquare, HPos.CENTER);
                        mapGrid.add(plantSquare, finalI - xMin + 1, yMax - finalJ + 1);
                    }

                }, ()->{
                    mapGrid.add(new Label(""), finalI - xMin + 1, yMax - finalJ + 1);
                });


            }
        }
    }

    private Circle getCircle(Animal el, int size) {
        Animal animal = el;
        double energyRatio = (double) animal.getEnergy() / simulation.getCofig().getEnergyToBeFull();
        Color animalColor;
        if (animal.isDead()) {
            animalColor = Color.BLACK;
        }
        else {
            animalColor = Color.color(Math.max(0, 1.0 - energyRatio), 0, 0);
        }
        Circle animalCircle = new Circle(size / 2.0, animalColor);
        return animalCircle;
    }

    private void handleCellClick(Animal animal) {
        selectedAnimal = animal;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        Thread thread = new Thread(simulation);
        thread.start();
    }

    @Override
    public void dayPassed(WorldMap map, SimulationStatsRecord stats) {
        Platform.runLater(() -> {
            statisticsLabel.setText(stats.toString());
            drawMap();
            updateAnimalInfo();
        });
    }

    private void updateAnimalInfo() {
        if (selectedAnimal != null) {
            animalInfo.setText(selectedAnimal.getInfo().toString());
        }
    }

    public void onClick(ActionEvent actionEvent) {
        simulation.pause();
    }
}