package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;
    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String toString() {
        return "(%d, %d)".formatted(x,y);
    }
    public boolean precedes(Vector2d other) {
        return x <= other.getX() && y <= other.getY();
    }
    public boolean follows(Vector2d other) {
        return x >= other.getX() && y >= other.getY();
    }
    public Vector2d add(Vector2d other) {
        return new Vector2d(x+other.getX(), y+ other.y);
    }
    public Vector2d subtract(Vector2d other){
        return  new Vector2d(x-other.getX(), y- other.getY());
    }
    public Vector2d upperRight(Vector2d other){
        int x1 = Math.max(x, other.getX());
        int y1 = Math.max(y, other.getY());
        return new Vector2d(x1,y1);
    }
    public Vector2d lowerLeft(Vector2d other){
        int x1 = Math.min(x, other.getX());
        int y1 = Math.min(y, other.getY());
        return new Vector2d(x1,y1);
    }
    public Vector2d opposite(){
        return new Vector2d(-x,-y);
    }
    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Vector2d)){
            return false;
        }
        Vector2d that = (Vector2d) other;
        return that.getY() == y && that.getX() == x;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }



}
