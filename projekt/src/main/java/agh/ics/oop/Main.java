package agh.ics.oop;

import javafx.application.Application;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        var conf = new SimulationConfig(5, 5, "", 4, 3, 6
//                , "", 5, 100, 20, 5, 1, 4, 0, 8, "");
//        var sim = new Simulation(new EarthMap(5, 5), conf, new NormalGenomesFactory(), true);
//        sim.run();
//        var conf = new SimulationConfig(5, 5, "", 4, 3, 2
//                , "", 5, 100, 20, 5, 1, 4, 0, 8, "");
//        var sim = new Simulation(new EarthMap(5, 5), conf, new NormalGenomesFactory(), true);
//        sim.run();

        Application.launch(ConfigApp.class, args);
    }
}