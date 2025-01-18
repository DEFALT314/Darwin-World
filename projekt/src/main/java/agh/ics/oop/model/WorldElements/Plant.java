package agh.ics.oop.model.WorldElements;

public class Plant implements WorldElement {
    private final Vector2d position;
    private final int energy;

    public Plant(Vector2d position, int energy) {
        this.energy = energy;
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }


    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "*";
    }
}
