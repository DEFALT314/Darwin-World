package agh.ics.oop.model;

import agh.ics.oop.model.WorldElements.Vector2d;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d position) {
        super("Position " + position + " is not correct");
    }
}
