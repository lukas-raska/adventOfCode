package year_2015.day_9;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Location {
    private final String name;
    private final Map<String, Integer> distances;

    public Location(String name) {
        this.name = name;
        this.distances = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getDistances() {
        return new HashMap<>(distances);
    }

    public void setDistance(String target,
                            int distance) {
        this.distances.put(target, distance);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        return name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name +
                "\n" +
                this.distances.entrySet().stream()
                        .map(e -> "--> to %s : %d".formatted(e.getKey(), e.getValue()))
                        .collect(Collectors.joining("\n"));

    }
}
