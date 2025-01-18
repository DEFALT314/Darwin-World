package agh.ics.oop.model.Simulation;

import agh.ics.oop.model.Map.WorldMap;
import agh.ics.oop.model.Stats.SimulationStats;
import agh.ics.oop.model.Stats.SimulationStatsRecord;

public interface SimulationListener {
    public void dayPassed(WorldMap map, SimulationStatsRecord stats);
}
