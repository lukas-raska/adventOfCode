package year_2025.day_10;

import java.util.Arrays;


public record Machine(String lights, int[][] buttons, int[] joltage) {

    private static int[] parseIntArray(String data) {
        return Arrays.stream(data.trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static Machine parse(String data) {

        String lights = data.substring(data.indexOf("[") + 1, data.indexOf("]"));

        String buttonsData = data.substring(data.indexOf("(") + 1, data.lastIndexOf(")")).replaceAll("[)(]", "");
        int[][] buttons = Arrays.stream(buttonsData.trim().split(" "))
                .map(Machine::parseIntArray)
                .toArray(int[][]::new);

        String joltageData = data.substring(data.indexOf("{") + 1, data.indexOf("}"));
        int[] joltage = parseIntArray(joltageData);

        return new Machine(lights, buttons, joltage);
    }
}
