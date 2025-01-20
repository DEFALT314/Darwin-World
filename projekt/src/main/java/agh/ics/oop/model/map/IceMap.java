package agh.ics.oop.model.map;

import agh.ics.oop.model.worldelements.Animal;
import agh.ics.oop.model.worldelements.Vector2d;

public class IceMap extends AbstractMap {
    private final int center;

    public IceMap(int width, int height) {
        super(width, height);
        center = (width - 1) / 2;
    }

    @Override
    protected void reduceEnergyToMove(Animal animal) {
        Vector2d pos = animal.getPosition();
        animal.subtractEnergy(Math.abs(pos.getY() - center) + 1);
    }
}
