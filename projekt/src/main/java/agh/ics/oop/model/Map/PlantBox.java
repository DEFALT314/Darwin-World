package agh.ics.oop.model.Map;

import agh.ics.oop.model.WorldElements.Plant;

import java.util.Optional;

public class PlantBox {
    private Plant plant;

    public boolean isPlanted() {
        return plant != null;
    }

    public Optional<Plant> getPlant() {
        return Optional.ofNullable(plant);
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int consumePlant() {
        if (plant == null) return 0;
        int energy = plant.getEnergy();
        plant = null;
        return energy;
    }
}
