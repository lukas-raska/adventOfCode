package year_2025.day_7;

import common.Solver;

import java.util.List;

public class Day7Solver implements Solver<Long,Long> {

    private final List<String> diagram;

    Day7Solver(List<String> puzzleInput){
        this.diagram = puzzleInput;
    }


    void printDiagram(){
        diagram.forEach(System.out::println);
    }

    @Override
    public Long part1() {
        return 0L;
    }

    @Override
    public Long part2() {
        return 0L;
    }
}
