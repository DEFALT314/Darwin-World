package agh.ics.oop.model.WorldElements;

import agh.ics.oop.model.Genomes.GenomesAbstract;

import java.util.HashSet;
import java.util.Set;

public class AnimalInfo {
    private int energy = 0;
    private int childCount = 0;
    private Animal parentOne;
    private Animal parentTwo;
    private int descendantCount = 0;
    private int deathDay = 0;
    private boolean isDead = false;
    private int plantEaten = 0;
    private int day;
    private final GenomesAbstract genome;
    private final int energyToBeFull;

    public AnimalInfo(GenomesAbstract genome, int energyToBeFull) {
        this.genome = genome;
        this.energyToBeFull = energyToBeFull;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy < 0) {
            markAsDead();
        }
    }

    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy < 0) {
            markAsDead();
        }
    }

    public void subtractEnergy(int energy) {

        this.energy -= energy;
        if (this.energy < 0) {
            markAsDead();
        }
    }

    public int getChildrenCnt() {
        return childCount;
    }

    public void addPlantEaten() {
        this.plantEaten += 1;
    }

    public int getChildCount() {
        return childCount;
    }

    public void incrementDescendantAndChildCount() {
        incrementChildCount();
        incrementDescendantCount(new HashSet<>());
    }

    private void incrementChildCount() {
        childCount++;
    }

    public int getDescendantCount() {
        return descendantCount;

    }

    private void incrementDescendantCount(Set<AnimalInfo> visited) {
        if (visited.contains(this)) {
            return;
        }

        visited.add(this);

        descendantCount++;

        if (parentOne != null) {
            parentOne.getInfo().incrementDescendantCount(visited);
        }
        if (parentTwo != null) {
            parentTwo.getInfo().incrementDescendantCount(visited);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public void markAsDead() {
        this.isDead = true;
        this.deathDay = day;
    }

    public void markAsDead(int deathDay) {
        this.isDead = true;
        this.deathDay = deathDay;
    }

    public int getDay() {
        return day;
    }

    public int getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(int deathDay) {
        this.isDead = true;
        this.deathDay = deathDay;
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

    public void setParentOne(Animal parentOne) {
        this.parentOne = parentOne;
    }

    public Animal getParentTwo() {
        return parentTwo;
    }

    public void setParentTwo(Animal parentTwo) {
        this.parentTwo = parentTwo;
    }

    public void incrementDay() {
        day++;
    }

    @Override
    public String toString() {
            StringBuilder info = new StringBuilder();
            info.append("Genome: ").append(genome).append("\n");
            info.append("Active Gene: ").append(genome.getActiveGenome()).append("\n");
            info.append("Energy: ").append(getEnergy()).append("\n");
            info.append("Plants Eaten: ").append(plantEaten).append("\n");
            info.append("Children: ").append(childCount).append("\n");
            info.append("Descendants: ").append(descendantCount).append("\n");
            info.append("Age: ").append(day).append("\n");
            if (isDead()) {
                info.append("Died on Day: ").append(deathDay).append("\n");
            }
            return info.toString();
        }
    }
