package agh.ics.oop.model;

public class DeadAnimalsAgeTracker {
    private int deadAnimalsAgeSum = 0;
    private int deadAnimalsCount = 0;

    public void addDeadAnimalAge(Animal animal) {
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
