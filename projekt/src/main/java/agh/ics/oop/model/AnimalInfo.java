package agh.ics.oop.model;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class AnimalInfo {
    private int energy = 0;
    private int childCount = 0;
    private Animal parentOne;
    private Animal parentTwo;
    private int descendantCount = 0;
    private int deathDay = 0;
    private boolean isDead = false;
    private int plantEaten = 0;

    public void setParentOne(Animal parentOne) {
        this.parentOne = parentOne;
    }
    public int getEnergy() {
        return energy;
    }
    public void addEnergy(int energy) {
        this.energy += energy;
    }
    public void subtractEnergy(int energy) {
        this.energy -= energy;
    }
    public int getChildrenCnt() {
        return childCount;
    }


    public void setDead(boolean dead) {
        this.isDead = dead;
    }
    public void setDeathDay(int deathDay) {
        this.isDead = true;
        this.deathDay = deathDay;
    }


    public void addPlantEaten() {
        this.plantEaten += 1;
    }

    public void setParentTwo(Animal parentTwo) {
        this.parentTwo = parentTwo;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getChildCount() {
        return childCount;
    }
    public void incrementDescendantAndChildCount() {
        incrementChildCount();
        incrementDescendantCount();
    }
    private void incrementChildCount() {
        childCount++;
    }

    public int getDescendantCount() {
        return descendantCount;
    }

    private void incrementDescendantCount() {
        descendantCount++;
    }

    public boolean isDead() {
        return isDead;
    }

    public void markAsDead(int deathDay) {
        this.isDead = true;
        this.deathDay = deathDay;
    }

    public int getDeathDay() {
        return deathDay;
    }

    public int getPlantEaten() {
        return plantEaten;
    }

    public void incrementPlantEaten() {
        plantEaten++;
    }

    public Animal getParentOne() {
        return parentOne;
    }

    public Animal getParentTwo() {
        return parentTwo;
    }
}