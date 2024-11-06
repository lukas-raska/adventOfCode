package year_2015.day_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RouteManager {
    private final List<Location> locations;

    public RouteManager(List<String> routeData) {
        this.locations = parseData(routeData);
    }

    private List<Location> parseData(List<String> routeData) {

        List<Location> locations = new ArrayList<>();

        for (var route : routeData) {

            String[] towns = route.split(" = ")[0].split(" to ");
            int distance = Integer.parseInt(route.split(" = ")[1]);

            for (int i = 0; i < towns.length; i++) {

                String startDest = towns[i];
                String finalDest = towns[(i + 1) % towns.length];

                boolean newLocationFound = locations
                        .stream()
                        .noneMatch(location -> location.getName().equals(startDest));

                Location processedLocation;
                if (newLocationFound) {
                    processedLocation = new Location(startDest);
                    locations.add(processedLocation);
                } else {
                    processedLocation = findLocationByName(locations, startDest);
                }

                processedLocation.setDistance(finalDest, distance);
            }
        }
        return locations;
    }

    private Location findLocationByName(List<Location> locations,
                                        String name) {
        return locations.stream()
                .filter(location -> location.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    private Location findLocationByName(String name) {
        return findLocationByName(this.locations, name);
    }


    public List<List<String>> findPossiblePaths() {

        List<String> allTowns = locations.stream()
                .map(Location::getName)
                .collect(Collectors.toList());

        List<List<String>> allPermutations = new ArrayList<>();
        generatePermutations(allTowns, allTowns.size(), allPermutations);

        return allPermutations;
    }

    /**
     * Find all possible permutations of elements order in given collection
     * uses Heap's algorithm (https://en.wikipedia.org/wiki/Heap%27s_algorithm)
     *
     * @param towns        given collection
     * @param size         size of given collection
     * @param permutations list for storing permutations
     */
    private void generatePermutations(List<String> towns,
                                      int size,
                                      List<List<String>> permutations) {
        if (size == 1) {
            permutations.add(new ArrayList<>(towns));
        } else {
            for (int i = 0; i < size; i++) {
                generatePermutations(towns, size - 1, permutations);
                int swapIndex = (size % 2 == 1) ? 0 : i; //if the size is odd, swap first and last element, else swap i-th and last
                Collections.swap(towns, swapIndex, size - 1);
            }
        }
    }

    public int getDistanceThroughAllTowns(List<String> towns) {
        int tripDistance = 0;
        String current = towns.get(0);
        var townsIterator = towns.listIterator(1);
        while (townsIterator.hasNext()) {
            String next = townsIterator.next();
            tripDistance += findLocationByName(current).getDistances().get(next);
            current = next;
        }
        return tripDistance;
    }

    public int getShortestDistanceThroughAllTowns() {
        return findPossiblePaths().stream()
                .mapToInt(this::getDistanceThroughAllTowns)
                .min()
                .orElseThrow();
    }

    public int getLongestDistanceThroughAllTowns() {
        return findPossiblePaths().stream()
                .mapToInt(this::getDistanceThroughAllTowns)
                .max()
                .orElseThrow();
    }


}
