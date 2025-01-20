package agh.ics.oop.model.map;

import agh.ics.oop.model.worldelements.Animal;

public class DeadAnimalsAgeTracker {
    private int deadAnimalsAgeSum = 0;
    private int deadAnimalsCount = 0;

    public void addDeadAnimal(Animal animal) {
        deadAnimalsAgeSum += animal.getAge();
        deadAnimalsCount++;
    }

    public int getDeadAnimalsAgeSum() {
        return deadAnimalsAgeSum;
    }

    public int getDeadAnimalsCount() {
        return deadAnimalsCount;
    }


}
