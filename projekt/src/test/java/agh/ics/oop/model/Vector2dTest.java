package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void twoVectorsAreEqual() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,2);
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));
    }
    @Test
    void twoVectorsAreNotEqual() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-1,2);
        assertFalse(v1.equals(v2));
        assertFalse(v2.equals(v1));
    }
    @Test
    void vectorAndOtherObjectAreNotEqual() {
        Vector2d v1 = new Vector2d(1,2);
        Object v2 = new Object();
        assertFalse(v1.equals(v2));
        assertFalse(v2.equals(v1));
    }
    @Test
    void toStringTest() {
        Vector2d v1 = new Vector2d(1,2);
        assertEquals("(1, 2)", v1.toString());
    }
    @Test
    void vectorPrecedesAnotherVector() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(0,0);
        assertTrue(v2.precedes(v1));
    }
    @Test
    void vectorDoesNotPrecedeAnotherVector() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,3);
        assertFalse(v2.precedes(v1));
    }
    @Test
    void vectorPrecedesItself() {
        Vector2d v1 = new Vector2d(1,2);
        assertTrue(v1.precedes(v1));
    }
    @Test
    void vectorFollowsAnotherVector() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(0,0);
        assertTrue(v1.follows(v2));
    }
    @Test
    void vectorDoesNotFollowAnotherVector() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,3);
        assertFalse(v1.follows(v2));
    }
    @Test
    void vectorFollowsItself() {
        Vector2d v1 = new Vector2d(1,2);
        assertTrue(v1.follows(v1));
    }
    @Test
    void upperRightTest() {
        Vector2d v1 = new Vector2d(0,3);
        Vector2d v2 = new Vector2d(3,0);
        assertEquals(new Vector2d(3, 3), v1.upperRight(v2));
    }
    @Test
    void lowerLeftTest() {
        Vector2d v1 = new Vector2d(0,3);
        Vector2d v2 = new Vector2d(3,0);
        assertEquals(new Vector2d(0,0), v1.lowerLeft(v2));
    }
    @Test
    void addTwoVectors() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,1);
        assertEquals(new Vector2d(2,3), v1.add(v2));
    }
    @Test
    void subtractTwoVectors() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,1);
        assertEquals(new Vector2d(0,1), v1.subtract(v2));
    }
    @Test
    void oppositeVector() {
        Vector2d v1 = new Vector2d(1,2);
        assertEquals(new Vector2d(-1,-2), v1.opposite());
    }
}