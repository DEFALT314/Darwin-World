package agh.ics.oop.model.Map;

import agh.ics.oop.model.WorldElements.Animal;

public class EarthMap extends AbstractMap {
    public EarthMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected void reduceEnergyToMove(Animal animal) {
        animal.subtractEnergy(1);
    }
}
