package year_2025.day_10;

import common.Solver;

import java.util.*;

public class Day10Solver implements Solver<Integer, Integer> {

    private final List<Machine> machines;


    public Day10Solver(List<String> inputData) {
        this.machines = inputData.stream()
                .map(Machine::parse)
                .toList();
    }


    private String getToggledLights(int[] button,
                                    String lights) {
        StringBuilder toggled = new StringBuilder(lights);
        for (int index : button) {
            toggled.setCharAt(index, lights.charAt(index) == '#' ? '.' : '#');
        }
        return toggled.toString();
    }

    @Override
    public Integer part1() {
        int sum = 0;
        for (Machine machine : machines) {
            int cnt = 0;
            Deque<String> current = new LinkedList<>();
            Deque<String> next = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            String start = ".".repeat(machine.lights().length());
            visited.add(start);
            current.add(start);
            boolean wasFound = false;
            while (!wasFound) {
                String lights = current.pop();
                for (var btn : machine.buttons()) {
                    String toggled = getToggledLights(btn, lights);
                    if (toggled.equals(machine.lights())) {
                        sum += cnt + 1;
                        wasFound = true;
                        break;
                    }
                    if (!visited.contains(toggled)) {
                        next.add(toggled);
                        visited.add(toggled);
                    }
                }
                if (current.isEmpty()) {
                    cnt++;
                    current = next;
                    next = new LinkedList<>();
                }
            }
        }
        return sum;

    }

    @Override
    public Integer part2() {
        return 0;
    }
}
