package agh.ics.oop.model.Genomes;

import agh.ics.oop.model.WorldElements.Animal;
import agh.ics.oop.model.Simulation.SimulationConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class GenomesAbstract {
    protected final SimulationConfig conf;
    protected List<Integer> genomes = new ArrayList<>();
    protected Random random = new Random();
    private int activeGenom;

    public GenomesAbstract(List<Integer> genomes, SimulationConfig conf) {
        this.conf = conf;
        this.genomes = genomes;
        activeGenom = 0;
    }

    public GenomesAbstract(SimulationConfig conf) {
        this.conf = conf;
        int n = conf.getGenomeLength();
        activeGenom = random.nextInt(n);
        for (int i = 0; i < n; i++) {
            genomes.add(random.nextInt(8));
        }
    }

    public GenomesAbstract(Animal animal1, Animal animal2, SimulationConfig conf) {
        this.conf = conf;
        Animal stronger = animal1.getInfo().getEnergy() >= animal2.getInfo().getEnergy() ? animal1 : animal2;
        Animal weaker = animal1.getInfo().getEnergy() < animal2.getInfo().getEnergy() ? animal1 : animal2;
        int strongerLength = (int) (conf.getGenomeLength() * ((double) stronger.getInfo().getEnergy() / (stronger.getInfo().getEnergy() + weaker.getInfo().getEnergy())));
        int weakerLength = conf.getGenomeLength() - strongerLength;
        int i = random.nextInt(2);
        if (i == 0) {
            genomes.addAll(stronger.getGenome().getGenomes().subList(0, strongerLength));
            genomes.addAll(weaker.getGenome().getGenomes().subList(0, weakerLength));
        } else {
            genomes.addAll(weaker.getGenome().getGenomes().subList(0, strongerLength));
            genomes.addAll(stronger.getGenome().getGenomes().subList(0, weakerLength));
        }
        mutate();

    }


    public void mutate() {
        int numberOfMutation = conf.getMinMutation() + random.nextInt(conf.getMaxMutation() - conf.getMinMutation() + 1);
        for (int i = 0; i < numberOfMutation; i++) {
            mutateOneGenome();
        }
    }

    public abstract void mutateOneGenome();

    public List<Integer> getGenomes() {
        return genomes;
    }

    public int getActiveGenom() {
        int oldGenom = activeGenom;
        activeGenom = (activeGenom + 1) % conf.getGenomeLength();
        return genomes.get(oldGenom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenomesAbstract that = (GenomesAbstract) o;
        return Objects.equals(genomes, that.genomes);
    }

    @Override
    public String toString() {
        return String.join("", genomes.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(genomes);
    }
}
