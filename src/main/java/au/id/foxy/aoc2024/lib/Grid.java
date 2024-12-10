package au.id.foxy.aoc2024.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Grid<T> implements Iterable<T> {
    private List<List<T>> grid;
    private final int width;
    private final int height;
    private Set<T> uniqueValues;
    private Map<T, List<Coord>> valueCoord;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new ArrayList<>(this.height);
        this.uniqueValues = new HashSet<>();
        this.valueCoord = new HashMap<>();

        for (int y = 0; y < this.height; y++) {
            this.grid.add(new ArrayList<>(this.width));
            for (int x = 0; x < this.width; x++) {
                this.grid.get(y).add(null);
            }
        }
    }

    public Grid(int width, int height, T initValue) {
        this(width, height);
        var iterator = new CoordIterator();
        while (iterator.hasNext()) {
            this.set(iterator.next(), initValue);
        }
    }

    public T get(Coord coord) {
        return grid.get(coord.getY()).get(coord.getX());
    }

    public void set(Coord coord, T value) {
        this.grid.get(coord.getY()).set(coord.getX(), value);
        this.uniqueValues.add(value);
        if (!this.valueCoord.containsKey(value))
            this.valueCoord.put(value, new ArrayList<>());
        this.valueCoord.get(value).add(coord);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Set<T> getUniqueValues() {
        return this.uniqueValues;
    }

    public List<Coord> getCoordsOfValue(T value) {
        return this.valueCoord.getOrDefault(value, null);
    }

    public boolean inBounds(Coord c) {
        return c.getX() >= 0 && c.getX() < this.width && c.getY() >= 0 && c.getY() < this.height; 
    }

    private class CoordIterator implements Iterator<Coord> {
        private int x = 0;
        private int y = 0;

        @Override
        public boolean hasNext() {
            return this.y < height;
        }

        @Override
        public Coord next() {
            Coord nextCoord;

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            nextCoord = new Coord(this.x++, this.y);
            if (this.x == width) {
                this.y++;
                this.x = 0;
            }
            return nextCoord;
        }
    }

    private class GridIterator implements Iterator<T> {
        private Iterator<Coord> coord = new CoordIterator();        

        @Override
        public boolean hasNext() {
            return coord.hasNext();
        }

        @Override
        public T next() {
            return Grid.this.get(coord.next());
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new GridIterator();
    }

    public Iterator<Coord> coordIterator() {
        return new CoordIterator();
    }
}