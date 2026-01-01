package year_2025.day_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Machine(String lights, List<int[]> buttons, int[] joltage) {

    public static Machine parse(String data) {
        String lights = null;
        List<int[]> buttons = null;
        int[] joltage = null;
        for (String s : data.split(" ")) {
            if (s.startsWith("[")) {
                lights = s.replaceAll("[\\[\\]]", "");

            }
            if (s.startsWith("(")) {
                int[] button = Arrays.stream(s.replaceAll("[()]", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if (buttons == null) {
                    buttons = new ArrayList<>();
                }
                buttons.add(button);
            }
            if (s.startsWith("{")) {
                joltage = Arrays.stream(s.replaceAll("[{}]", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
        Objects.requireNonNull(lights);
        Objects.requireNonNull(joltage);
        Objects.requireNonNull(buttons);

        return new Machine(lights, buttons, joltage);
    }
}
