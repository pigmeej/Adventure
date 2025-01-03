package com.blazi.adventure;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {

    public Game() {
        Set<Location> locations = new HashSet<>(MapData.loadMap());
        // Get random starting location
        Location currentLocation = locations.iterator().next();

        boolean exit = false;
        while (!exit) {
            render(currentLocation);
            String userInput = getUserInput();
            if (userInput.equalsIgnoreCase("Q")) { // Exit condition
                exit = true;
            } else {
                Location nextLocation = getNextLocation(currentLocation, userInput);
                if (nextLocation != null) {
                    currentLocation = nextLocation;
                }
            }
        }
    }

    private static void render(Location currentLocation) {
        System.out.printf("*** You're standing %s ***%n", currentLocation.getDescription());
        System.out.println("     From here you can see");
        for (Map.Entry<String, Location> n : currentLocation.getAllNeighbours().entrySet()) {
            System.out.printf("     - A %s (%s)%n", n.getValue(), n.getKey());
        }
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase().substring(0, 1);
    }

    private static Location getNextLocation(Location currentLocation, String dir) {
        if (!Location.validDirections.contains(dir)) {
            System.out.println("Invalid direction");
            return null;
        }
        return currentLocation.getNeighbour(dir);
    }
}
