package com.blazi.adventure;

import java.util.*;

public class MapData {
    public static String mapData = """
            road,at the end of the road, W: hill, E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest, E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley, N:well house""";

    public static Set<Location> loadMap() {
        Scanner scanner = new Scanner(mapData);
        TreeSet<Location> loadedLocations = new TreeSet<>();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            loadedLocations.add(new Location(line[0].trim(), line[1].trim()));
        }

        for (Location current : loadedLocations) {
            var neighbours = loadNeighbours(current.getName());
            for (var neighbour : neighbours.entrySet()) {
                current.addNeighbour(neighbour.getKey(), loadedLocations.floor(new Location(neighbour.getValue())));
            }
        }
        return loadedLocations;
    }

    public static Map<String, String> loadNeighbours(String key) {
        // K: Direction
        // V: neighbour name
        Scanner scanner = new Scanner(mapData);
        Map<String,String> neighbours = new HashMap<>();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            if (line[0].equals(key)) {
                for (int i = 2; i < line.length; i++) {
                    String k = line[i].substring(0, line[i].indexOf(':')).trim();
                    String v = line[i].substring(line[i].indexOf(':') + 1).trim();
                    neighbours.put(k, v);
                }
            }
        }

        return neighbours;
    }
}
