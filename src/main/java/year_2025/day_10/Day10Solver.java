package year_2025.day_10;

import common.Solver;

import java.util.*;

public class Day10Solver implements Solver<Integer, Integer> {

    private final Machine[] machines;

    public Day10Solver(List<String> inputData) {
        this.machines = inputData.stream()
                .map(Machine::parse)
                .toArray(Machine[]::new);
    }


    private String applyButtonToLights(int[] button,
                                       String lights) {
        StringBuilder toggled = new StringBuilder(lights);
        for (int idx : button) {
            toggled.setCharAt(idx, lights.charAt(idx) == '#' ? '.' : '#');
        }
        return toggled.toString();
    }

    private int[] applyButtonToJoltage(int[] button,
                                       int[] joltage) {
        int[] updated = Arrays.copyOf(joltage, joltage.length);
        for (int idx : button) {
            updated[idx]++;
        }
        return updated;
    }

    private int countSteps(Machine machine) {
        String target = machine.lights();
        String start = ".".repeat(target.length());
        int steps = 0;
        Set<String> currentLevel = new HashSet<>(Set.of(start));
        while (true) {
            steps++;
            Set<String> nextLevel = new HashSet<>();
            for (String lights : currentLevel) {
                for (int[] button : machine.buttons()) {
                    String updated = applyButtonToLights(button, lights);
                    if (updated.equals(machine.lights())) {
                        return steps;
                    }
                    nextLevel.add(updated);
                }
                currentLevel = nextLevel;
            }
        }
    }

    @Override
    public Integer part1() {
        return Arrays.stream(machines)
                .mapToInt(this::countSteps)
                .sum();

    }


    public int minSteps(Machine machine) {
        int[] start = new int[machine.joltage().length];
        int[] target = machine.joltage();
        int steps = 0;

        // fronta pro aktuální level: každý prvek = stav + kroky
        List<int[]> currentLevel = new ArrayList<>();
        currentLevel.add(start);

        while (!currentLevel.isEmpty()) {
            List<int[]> nextLevel = new ArrayList<>();
            for (int[] joltage : currentLevel) {
                for (int[] button : machine.buttons()) {
                    int[] updated = applyButtonToJoltage(button, joltage);

                    // overflow check
                    boolean overflow = false;
                    for (int i = 0; i < updated.length; i++) {
                        if (updated[i] > target[i]) {
                            overflow = true;
                            break;
                        }
                    }
                    if (overflow) {
                        continue;
                    }

                    // check cílového stavu
                    boolean done = true;
                    for (int i = 0; i < updated.length; i++) {
                        if (updated[i] != target[i]) {
                            done = false;
                            break;
                        }
                    }
                    if (done) {
                        return steps + 1;
                    }

                    // přidat do další úrovně
                    nextLevel.add(updated);
                }
            }

            steps++;
            currentLevel = nextLevel;
        }

        throw new IllegalStateException("Unreachable target: " + Arrays.toString(target));
    }

    private int[] applyButton(int[] state,
                              int[] btn) {
        int[] updated = Arrays.copyOf(state, state.length);
        for (int idx : btn) {
            updated[idx]++;
        }
        return updated;
    }


    @Override
    public Integer part2() {
        int sum = 0;
        for (Machine machine : machines) {
            sum += minSteps(machine);
        }
        return sum;
    }
}
