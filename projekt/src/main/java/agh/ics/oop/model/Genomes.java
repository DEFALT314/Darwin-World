package agh.ics.oop.model;

import java.util.List;
import java.util.Random;

public class Genomes {
    private int activeGenom;
    private List<Integer> genomes;
    private int n;
    private SimulationConfig conf;
    public Genomes(int n) {
        this.n = n;
        Random random = new Random();
        activeGenom = random.nextInt(n);
        for (int i = 0; i < n; i++) {
            genomes.add(random.nextInt(8));
        }
    }

    public Genomes(Animal animal, Animal parent, SimulationConfig conf) {
        this.conf = conf;
    }

    public int mutate(){

    }


    public int getActiveGenom() {
        int oldGenom = activeGenom;
        activeGenom = (activeGenom + 1) % n;
        return oldGenom;
    }
}
