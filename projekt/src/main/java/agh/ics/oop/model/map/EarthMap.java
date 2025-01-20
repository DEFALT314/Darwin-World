package agh.ics.oop.model.map;

import agh.ics.oop.model.worldelements.Animal;

public class EarthMap extends AbstractMap {
    public EarthMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected void reduceEnergyToMove(Animal animal) {
        animal.subtractEnergy(1);
    }
}
