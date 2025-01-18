package agh.ics.oop.model.Map;

import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.WorldElements.Vector2d;

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
