package year_2025.day_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Day2Solver {

    private final List<long[]> puzzleInput;

    public Day2Solver(String puzzleInput) {
        this.puzzleInput = Arrays.stream(puzzleInput.split(","))
                .map(this::parse)
                .toList();
    }

    public long solvePart1() {
        List<Long> invalidIds = new ArrayList<>();
        for (long[] range : puzzleInput) {
            List<Long> invalidInRange = getInvalidIdsInRange(range, this::isInvalid);
            invalidIds.addAll(invalidInRange);
        }
        return invalidIds.stream().reduce(0L, Long::sum);
    }

    public long solvePart2() {
        List<Long> invalidIds = new ArrayList<>();
        for (long[] range : puzzleInput) {
            List<Long> invalidInRange = getInvalidIdsInRange(range, this::isStillInvalid);
            invalidIds.addAll(invalidInRange);
        }
        return invalidIds.stream().reduce(0L, Long::sum);
    }

    List<Long> getInvalidIdsInRange(long[] range,
                                    Predicate<Long> validator) {
        List<Long> invalidIds = new ArrayList<>();
        for (long i = range[0]; i <= range[1]; i++) {
            if (validator.test(i)) {
                invalidIds.add(i);
            }
        }
        return invalidIds;
    }

    long[] parse(String input) {
        String[] split = input.split("-");
        return new long[]{Long.parseLong(split[0]), Long.parseLong(split[1])};
    }

    boolean isStillInvalid(long id) {
        String stringId = String.valueOf(id);
        int length = stringId.length();
        boolean foundInvalid = false;
        for (int parts = 2; parts <= length; parts++) {
            if (length % parts == 0) {
                int partLength = length / parts;
                String[] split = new String[parts];
                for (int i = 0, index = 0; i < parts; i++, index= i* partLength) {
                    split[i]= stringId.substring(index, index + partLength);
                }
                String prev = split[0];
                foundInvalid = true;
                for (int i = 1; i < split.length; i++) {
                    String current = split[i];
                    if (!current.equals(prev)) {
                        foundInvalid = false;
                        break;
                    }
                    prev = current;
                }
                if (foundInvalid) {
                    return true;
                }
            }
        }
        return foundInvalid;
    }

    private boolean isInvalid(long id) {
        String stringId = String.valueOf(id);
        int length = stringId.length();
        if (length % 2 != 0) {
            return false;
        }
        String head = stringId.substring(0, length / 2);
        String tail = stringId.substring(length / 2);

        return head.equals(tail);
    }


}

