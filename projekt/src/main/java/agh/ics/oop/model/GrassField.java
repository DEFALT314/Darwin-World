package agh.ics.oop.model;

import java.util.Collection;
import java.util.HashMap;

public class GrassField extends AbstractWorldMap{
    HashMap<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int count) {
        super();
        int max = (int) Math.sqrt(count*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(count,max, max );
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));}
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return super.objectAt(position) != null? super.objectAt(position):grasses.get(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d min  = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d max  = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (WorldElement element : getElements()) {
            Vector2d location = element.getPosition();
            min = location.lowerLeft(min);
            max = location.upperRight(max);
        }
        return new Boundary(min, max);
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> result = super.getElements();
        result.addAll(grasses.values());
        return result;
    }


}
