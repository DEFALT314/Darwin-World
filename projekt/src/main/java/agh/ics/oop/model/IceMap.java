package agh.ics.oop.model;

public class IceMap extends AbstractMap{
    private final int center;
    public IceMap(int width, int height) {
        super(width, height);
        center = (width-1) / 2;
    }

    @Override
    protected void reduceEnergyToMove(Animal animal) {
        Vector2d pos = animal.getPosition();
        animal.getInfo().subtractEnergy(Math.abs(pos.getY()-center)+1);
    }
}
