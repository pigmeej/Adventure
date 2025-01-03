package com.blazi.adventure;

import java.util.*;

public class Location implements Comparable<Location> {

    public static final Set<String> validDirections;

    static {
        validDirections = new HashSet<>(List.of("N", "E", "S", "W"));
    }

    private Map<String, Location> neighbours;
    private final String name;
    private final String description;

    public Location(String name) {
        this(name, "no description");
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.neighbours = new HashMap<>();
    }

    public boolean addNeighbour(String dir, Location newNeighbour) {
        String dirShort = dir.toUpperCase().substring(0, 1);
        if (!validDirections.contains(dirShort)) {
            System.out.println("Invalid direction");
            return false;
        }

        if (neighbours.putIfAbsent(dirShort, newNeighbour) == null) {
            newNeighbour.addNeighbour(getOppositeDir(dirShort), this);
            return true;
        } else {
            return false;
        }
    }

    private static String getOppositeDir(String dir) {
        return switch (dir) {
            case "N" -> "S";
            case "S" -> "N";
            case "W" -> "E";
            case "E" -> "W";
            default -> "";
        };
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getNeighbour(String dir) {
        return neighbours.get(dir.toUpperCase().substring(0, 1));
    }

    public Map<String, Location> getAllNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
