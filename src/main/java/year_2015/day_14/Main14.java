package year_2015.day_14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main14 {

    static int RACE_TIME = 2503;

    public static void main(String[] args) {

        //part 1
        var reindeer = Data.getPuzzleInput().lines()
                .map(Reindeer::parse)
                .toList();

        var distanceMap = reindeer.stream()
                .collect(Collectors.toMap(
                        Reindeer::name,
                        r -> r.calculateDistance(RACE_TIME)));

        int maxDistance = distanceMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        //part 2
        int maxScore = countScore(reindeer).entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .max()
                .orElseThrow();

        System.out.println("The answer of the day 14:");
        System.out.println("Part 1: " + maxDistance); //2655
        System.out.println("Part 2: " + maxScore); //1059


    }

    public static Map<Reindeer, Integer> countScore(List<Reindeer> competitors) {
        Map<Reindeer, Integer> scoreMap = competitors.stream()
                .collect(Collectors.toMap(
                        r -> r,
                        r -> 0)
                );

        Map<Reindeer, Integer> distanceMap = new HashMap<>(Map.copyOf(scoreMap));

        for (int time = 1; time <= RACE_TIME; time++) {
            int elapsed = time;
            //compute distances in each second
            competitors.forEach(reindeer -> {
                distanceMap.merge(
                        reindeer,
                        reindeer.calculateDistance(elapsed),
                        (oldValue, newValue) -> newValue
                );
            });

            //find current max value distance
            int maxCurrentDistance = distanceMap.values().stream()
                    .max(Integer::compare)
                    .orElseThrow();

            //add point to reindeer with this max distance
            distanceMap.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxCurrentDistance)
                    .map(Map.Entry::getKey)
                    .forEach(reindeer -> scoreMap.merge(
                            reindeer,
                            1,
                            Integer::sum));
        }
        return scoreMap;
    }
}
