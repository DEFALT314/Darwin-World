package agh.ics.oop.model.simulation;

import agh.ics.oop.model.map.WorldMap;
import agh.ics.oop.model.stats.SimulationStatsRecord;

public interface SimulationListener {
    public void dayPassed(WorldMap map, SimulationStatsRecord stats);
}
