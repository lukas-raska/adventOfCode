package year_2015.day_14;

import java.util.stream.Collectors;

public class Main14 {

    static int RACE_TIME = 2503;

    public static void main(String[] args) {

        var reindeer = Data.getPuzzleInput().lines()
                .map(Reindeer::parse)
                .toList();


        var distanceMap = reindeer.stream()
                .collect(Collectors.toMap(
                        Reindeer::name,
                        r -> r.countDistance(RACE_TIME)));

        int maxDistance = distanceMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        System.out.println("The answer of the day 14:");
        System.out.println("Part 1: " + maxDistance); //2655


    }
}
