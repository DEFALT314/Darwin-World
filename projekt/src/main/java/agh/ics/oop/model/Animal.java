package agh.ics.oop.model;

import java.util.Random;

public class Animal implements WorldElement{
    private final Genomes genome;
    private MapDirection orientation;
    private Vector2d localisation;
    private int energy= 0;
    private int chldrenCnt = 0;
    private Animal parentOne;
    private Animal parentTwo;
    private int descendantCnt= 0;
    private int deathDay = 0;
    private boolean isDead = false;
    public Animal(Vector2d localisation, int energy, int n){
        Random random = new Random();
        this.orientation = MapDirection.values()[random.nextInt(8)];
        this.localisation = localisation;
        this.energy = energy;
        this.genome = new Genomes(n);
    }

    @Override
    public String toString() {
        return orientation.toString();
    }
    public boolean isAt(Vector2d position){
        return  localisation.equals(position);
    }
    public void move(MoveValidator validator, Boundary boundary){
        int activeGenom = genome.getActiveGenom();
        for (int i = 0; i < activeGenom; i++) {
            orientation = orientation.next();
        }
        Vector2d newPosition = localisation.add(orientation.toUnitVector());




    }
    public Vector2d getPosition() {
        return localisation;
    }
    public MapDirection getOrientation() {
        return orientation;
    }
}

