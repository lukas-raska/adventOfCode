package year_2024.day_11;

import java.util.*;
import java.util.stream.Collectors;

public class Day11Solver {

    private final List<Long> stones;

    private final static Map<Long, List<Integer>> DIGITS_CACHE = new HashMap<>();

    public Day11Solver(String puzzleInput) {
        this.stones = parse(puzzleInput);
    }

    public long solvePart1() {

        long result = 0;
        for (var stone : this.stones) {
            var currentStoneSize = blink(stone, 25);
            result += currentStoneSize;
        }
        return result;
    }

    public long blink(long number,
                      int stepsLimit) {
        List<Long> stones = new ArrayList<>();
        List<Long> newStones = new ArrayList<>();
        stones.add(number);
        for (int step = 0; step < stepsLimit; step++) {

            stones.addAll(newStones);
            newStones.clear();

            for (int j = 0; j < stones.size(); j++) {
                long current = stones.get(j);
                if (current == 0) {
                    stones.set(j, 1L);
                } else {
                    List<Integer> digits;
                    if (DIGITS_CACHE.containsKey(current)) {
                        digits = DIGITS_CACHE.get(current);
                    } else {
                        digits = numberToDigits(current);
                        DIGITS_CACHE.put(current, digits);
                    }

                    if (digits.size() % 2 == 0) {
                        long firstPart = digitsToNumber(digits.subList(0, digits.size() / 2));
                        long secondPart = digitsToNumber(digits.subList(digits.size() / 2, digits.size()));
                        stones.set(j, firstPart);
                        newStones.add(secondPart);
                    } else {
                        stones.set(j, current * 2024L);
                    }
                }



            }
        }
        return stones.size();
    }

//

    private List<Integer> numberToDigits(long number) {
        if (number == 0) {
            return List.of(0);
        }
        List<Integer> digits = new ArrayList<>();
        while (number != 0) {
            digits.addFirst((int) number % 10);
            number /= 10;
        }
        return digits;
    }

    private long digitsToNumber(List<Integer> digits) {
        long result = 0;
        long exp = 1;
        for (int i = digits.size() - 1; i >= 0; i--) {
            result += exp * digits.get(i);
            exp *= 10;
        }
        return result;
    }

    private List<Long> parse(String puzzleInput) {
        return Arrays.stream(puzzleInput.split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
