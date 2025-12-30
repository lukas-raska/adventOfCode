package year_2025.day_10;

import common.Solver;

import java.util.List;

public class Day10Solver implements Solver<Integer, Integer> {

    private final List<Machine> machines;

    public Day10Solver (List<String> inputData){
        this.machines = inputData.stream()
                .map(Machine::parse)
                .toList();
    }

    private int getMinPresses(){
        return 0;
    }

    @Override
    public Integer part1() {
        return 0;
    }

    @Override
    public Integer part2() {
        return 0;
    }
}
