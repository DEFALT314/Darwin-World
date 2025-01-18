package agh.ics.oop;

import agh.ics.oop.model.Map.EarthMap;
import agh.ics.oop.model.Genomes.NormalGenomesFactory;
import agh.ics.oop.model.Simulation.Simulation;
import agh.ics.oop.model.Simulation.SimulationConfig;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        var conf = new SimulationConfig(5, 5, "", 4, 3, 6
//                , "", 5, 100, 20, 5, 1, 4, 0, 8, "");
//        var sim = new Simulation(new EarthMap(5, 5), conf, new NormalGenomesFactory(), true);
//        sim.run();
        var conf = new SimulationConfig(5, 5, "", 4, 3, 2
                , "", 5, 100, 20, 5, 1, 4, 0, 8, "");
        var sim = new Simulation(new EarthMap(5, 5), conf, new NormalGenomesFactory(), true);
        sim.run();
    }
}