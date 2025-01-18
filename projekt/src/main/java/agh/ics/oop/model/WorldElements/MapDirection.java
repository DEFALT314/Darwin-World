package agh.ics.oop.model.WorldElements;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST;

    public String toString() {
        return switch (this) {
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
            case NORTHEAST -> "NE";
            case SOUTHEAST -> "SE";
            case SOUTHWEST -> "SW";
            case NORTHWEST -> "NW";

        };

    }

    public MapDirection next() {
        return switch (this) {
            case EAST -> SOUTHEAST;
            case SOUTHEAST -> SOUTH;
            case SOUTH -> SOUTHWEST;
            case SOUTHWEST -> WEST;
            case WEST -> NORTHWEST;
            case NORTHWEST -> NORTH;
            case NORTH -> NORTHEAST;
            case NORTHEAST -> EAST;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case EAST -> NORTHEAST;
            case NORTHEAST -> NORTH;
            case NORTH -> NORTHWEST;
            case NORTHWEST -> WEST;
            case WEST -> SOUTHWEST;
            case SOUTHWEST -> SOUTH;
            case SOUTH -> SOUTHEAST;
            case SOUTHEAST -> EAST;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case NORTHEAST -> new Vector2d(1, 1);
            case SOUTHEAST -> new Vector2d(1, -1);
            case SOUTHWEST -> new Vector2d(-1, -1);
            case NORTHWEST -> new Vector2d(-1, 1);
        };
    }

}
