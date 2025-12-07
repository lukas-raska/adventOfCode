package year_2025.day_3;

import common.utils.MathUtils;

import java.util.List;

public class Day3Solver {

    private final List<String> puzzleInput;

    public Day3Solver(List<String> puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    int solvePart1() {
        return this.puzzleInput.stream()
                .map(this::getMaxJoltage)
                .reduce(0, Integer::sum);
    }

    long solvePart2(){
        return this.puzzleInput.stream()
                .map(this::getSuperMaxJoltage)
                .reduce(0L, Long::sum);
    }

    long getSuperMaxJoltage(String battery) {
        int length = 12;
        int[] values = new int[length];
        int lastMaxIdx = -1;
        for (int i = 0; i < values.length; i++) {
            for (int j = lastMaxIdx + 1; j <= battery.length() - length; j++) {
                int current = Integer.parseInt(String.valueOf(battery.charAt(j)));
                if (current > values[i]) {
                    values[i] = current;
                    lastMaxIdx = j;
                    if (current == 9) {
                        break;
                    }
                }
            }
            length--;
        }
        return MathUtils.digitsToNumber(values);
    }


    int getMaxJoltage(String battery) {
        int first = 0;
        int second = 0;
        int firstIndex = 0;
        for (int i = 0; i < battery.length() - 1; i++) {
            int current = Integer.parseInt(String.valueOf(battery.charAt(i)));
            if (current > first) {
                first = current;
                firstIndex = i;
                if (first == 9) {
                    break;
                }
            }
        }
        for (int i = firstIndex + 1; i < battery.length(); i++) {
            int current = Integer.parseInt(String.valueOf(battery.charAt(i)));
            if (current > second) {
                second = current;
                if (second == 9) {
                    break;
                }
            }
        }
        return 10 * first + second;
    }
}
