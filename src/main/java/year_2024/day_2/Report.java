package year_2024.day_2;

import java.util.Arrays;
import java.util.List;

public record Report(List<Integer> levels) {
    private static final int SAFE_STEP = 3;

    public Report(String inputData) {
        this(Arrays
                .stream(inputData.split(" "))
                .map(Integer::parseInt)
                .toList());

    }

    public boolean isSafe() {
        int current, next;
        boolean isIncreasing = this.levels().get(0) < this.levels().get(1);

        for (int i = 0; i < this.levels().size() - 1; i++) {
            current = this.levels().get(i);
            next = this.levels().get(i + 1);
            if (current == next ||
                    Math.abs(next - current) > SAFE_STEP ||
                    isIncreasing && next < current ||
                    !isIncreasing && next > current) {
                return false;
            }
        }
        return true;
    }
}

