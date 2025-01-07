package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void nextDirectionForEachDirection() {
        assertEquals(MapDirection.NORTHEAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTHEAST.next());
        assertEquals(MapDirection.SOUTHEAST, MapDirection.EAST.next());
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHEAST.next());
        assertEquals(MapDirection.SOUTHWEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTHWEST.next());
        assertEquals(MapDirection.NORTHWEST, MapDirection.WEST.next());
        assertEquals(MapDirection.NORTH, MapDirection.NORTHWEST.next());
    }

    @Test
    void previousDirectionForEachDirection() {
        assertEquals(MapDirection.NORTHWEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTHWEST.previous());
        assertEquals(MapDirection.SOUTHWEST, MapDirection.WEST.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHWEST.previous());
        assertEquals(MapDirection.SOUTHEAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTHEAST.previous());
        assertEquals(MapDirection.NORTHEAST, MapDirection.EAST.previous());
        assertEquals(MapDirection.NORTH, MapDirection.NORTHEAST.previous());
    }

}