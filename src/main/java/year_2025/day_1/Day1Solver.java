package year_2025.day_1;

import java.util.List;

public class Day1Solver {

    private final int[] puzzleInput;
    private final int STARTING_POSITION = 50;
    private final int DIAL_LENGTH = 100;

    public Day1Solver(List<String> puzzleInput) {
        this.puzzleInput = puzzleInput.stream()
                .mapToInt(Day1Solver::parseInput)
                .toArray();
    }

    int solvePart1() {
        int position = STARTING_POSITION;
        int zerosTouch = 0;
        for (int instruction : this.puzzleInput) {
            position += instruction;
            position %= DIAL_LENGTH;
            if (position == 0) {
                zerosTouch++;
            }
        }
        return zerosTouch;
    }

    int solvePart2() {
        int position = STARTING_POSITION;
        int zeros = 0;
        for (int instruction : puzzleInput) {
            int step = (instruction < 0) ? -1 : 1;
            for (int i = 0; i < Math.abs(instruction); i++) {
                position += step;
                position %= DIAL_LENGTH;
                if (position == 0) {
                    zeros++;
                }
            }
        }
        return zeros;
    }


    private static int parseInput(String instruction) {
        int dir = switch (instruction.charAt(0)) {
            case 'L' -> -1;
            case 'R' -> 1;
            default -> throw new IllegalArgumentException("Unknown instruction: " + instruction);
        };
        int value = Integer.parseInt(instruction.substring(1));
        return dir * value;
    }
}






