package agh.ics.oop.presenter;

import agh.ics.oop.SimulationApp;
import agh.ics.oop.model.Map.Boundary;
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
import java.util.Optional;
public class SimulationPresenter implements SimulationListener {
    public GridPane mapGrid;
    public Label statisticsLabel;
    public Label animalInfo;
    private Simulation simulation;
    private Animal selectedAnimal;
    private int size = 20;



    public void drawMap() {
        var worldMap = simulation.getWorldMap();
        clearGrid();
        double cellWidth = mapGrid.getWidth() / (worldMap.getCurrentBounds().upperRight().getX() - worldMap.getCurrentBounds().downLeft().getX() + 2);
        double cellHeight = mapGrid.getHeight() / (worldMap.getCurrentBounds().upperRight().getY() - worldMap.getCurrentBounds().downLeft().getY() + 2);
        size = (int) Math.min(cellWidth, cellHeight);

        mapGrid.getColumnConstraints().add(new ColumnConstraints(size));
        mapGrid.getRowConstraints().add(new RowConstraints(size));
        var bounds = worldMap.getCurrentBounds();
        Label label1 = new Label("y/x");
        GridPane.setHalignment(label1, HPos.CENTER);
        mapGrid.add(label1, 0, 0);
        int row = 1;
        int yMax = bounds.upperRight().getY();
        int yMin = bounds.downLeft().getY();
        for (int i = yMax; i >= yMin; i--) {
            Label label = new Label("%d".formatted(i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(size));
            mapGrid.add(label, 0, row);
            row++;
        }
        int column = 1;
        int xMin = bounds.downLeft().getX();
        int xMax = bounds.upperRight().getX();
        for (int i = xMin; i <= xMax; i++) {
            Label label = new Label("%d".formatted(i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(size));
            mapGrid.add(label, column, 0);
            column++;
        }
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d p = new Vector2d(i, j);
                int finalJ = j;
                int finalI = i;
                if (worldMap.isPlanted(p)) {
                    Rectangle plantSquare = new Rectangle(size, size, Color.GREEN);
                    GridPane.setHalignment(plantSquare, HPos.CENTER);
                    mapGrid.add(plantSquare, finalI - xMin + 1, yMax - finalJ + 1);
                }
                Optional<Animal> animal = worldMap.getStrongestAnimalAt(p);
                animal.ifPresent((Animal a) -> {
                    Circle shape = getCircle(a, size);
                    shape.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleCellClick(a));
                    GridPane.setHalignment(shape, HPos.CENTER);
                    mapGrid.add(shape, finalI - xMin + 1, yMax - finalJ + 1);
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
        } else {
            animalColor = Color.color(Math.max(0, 1.0 - energyRatio), 0, 0);
        }
        Circle animalCircle = new Circle(size / 2.0, animalColor);
        return animalCircle;
    }

    private void handleCellClick(Animal animal) {
        selectedAnimal = animal;
        updateAnimalInfo();
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
        Boundary bounds = simulation.getWorldMap().getCurrentBounds();
        int yMax = bounds.upperRight().getY();
        int xMin = bounds.downLeft().getX();
        if (!simulation.isActive()) {
            List<Vector2d> preferredPlantLocations = simulation.getPreferredPlantLocations();
            for (Vector2d location : preferredPlantLocations) {
                Rectangle overlay = new Rectangle(size, size);
                overlay.setFill(Color.LIGHTGREEN);
                overlay.setMouseTransparent(true);
                overlay.setOpacity(0.3);
                GridPane.setHalignment(overlay, HPos.CENTER);
                mapGrid.add(overlay, location.getX() - xMin + 1, yMax - location.getY() + 1);
            }
            var positions = simulation.getAnimalsWithCommonGeonome().stream().map(Animal::getPosition).toList();
            for (Vector2d position : positions) {
                Rectangle overlay = new Rectangle(size, size);
                overlay.setFill(Color.AQUA);
                overlay.setMouseTransparent(true);
                overlay.setOpacity(0.3);
                GridPane.setHalignment(overlay, HPos.CENTER);
                mapGrid.add(overlay, position.getX() - xMin + 1, yMax - position.getY() + 1);
            }
        }
    }
}