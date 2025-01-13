package agh.ics.oop.model;

import java.util.Optional;

public class PlantContainer {
    private Plant plant;

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public boolean isPlanted() {
        return plant != null;
    }

    public boolean isEmpty() {
        return plant == null;
    }

    public Optional<Plant> getPlant() {
        return Optional.ofNullable(plant);
    }

    public int consumePlant() {
        if (plant == null) return 0;
        int energy = plant.getEnergy();
        plant = null;
        return energy;
    }
}
