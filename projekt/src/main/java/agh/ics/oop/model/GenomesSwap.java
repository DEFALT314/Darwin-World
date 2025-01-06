package agh.ics.oop.model;

public class GenomesSwap extends GenomesAbstract {


    public GenomesSwap(Animal animal1, Animal animal2, SimulationConfig conf) {
        super(animal1, animal2, conf);
    }



    public GenomesSwap(SimulationConfig conf) {
        super(conf);
    }

    @Override
    public void mutateOneGenome() {
        int i = random.nextInt(conf.getGenomeLength());
        int j = random.nextInt(conf.getGenomeLength());
        int temp = genomes.get(i);
        genomes.set(i,genomes.get(j));
        genomes.set(j,temp);

    }
}
