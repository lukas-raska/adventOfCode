package year_2025.day_7;

import common.Solver;

import java.util.*;

public class Day7Solver implements Solver<Integer, Long> {

    private final List<String> diagram;

    Day7Solver(List<String> puzzleInput) {
        this.diagram = new ArrayList<>(puzzleInput);
    }


    public void printDiagram() {
        diagram.forEach(System.out::println);
    }

    @Override
    public Integer part1() {
        int splitCnt = 0;
        final Set<Integer> beamIndices = new HashSet<>(List.of(diagram.getFirst().indexOf('S')));
        for (int row = 1; row < diagram.size(); row++) {

            Set<Integer> indicesToAdd = new HashSet<>();
            Set<Integer> indicesToRemove = new HashSet<>();

            for (int beamIndex : beamIndices) {

                if (diagram.get(row).charAt(beamIndex) == '^') {
                    int newIndex1 = beamIndex - 1;
                    int newIndex2 = beamIndex + 1;
                    if (newIndex1 > 0) {
                        indicesToAdd.add(newIndex1);
                    }
                    if (newIndex2 < diagram.get(row).length()) {

                        indicesToAdd.add(newIndex2);
                    }
                    splitCnt++;
                    indicesToRemove.add(beamIndex);
                }
            }
            beamIndices.removeAll(indicesToRemove);
            beamIndices.addAll(indicesToAdd);
        }
        return splitCnt;
    }

    @Override
    public Long part2() {
        long result = 0;
        int startIndex = diagram.getFirst().indexOf('S');
        long[] beamPathsCounts = new long[diagram.getFirst().length()];
        beamPathsCounts[startIndex] = 1;
        for (int row = 1; row < diagram.size(); row++) {
            adjustBeamPathsCount(beamPathsCounts, row);
        }
        for (long beamPathsCount : beamPathsCounts) {
            result += beamPathsCount;
        }
        return result;
    }


    private void adjustBeamPathsCount(long[] beamPathsCounts,
                                      int row) {
        String line = diagram.get(row);
        int length = line.length();
        long[] adj = new long[length];
        boolean adjusted = false;
        for (int col = 0; col < length; col++) {
            if (line.charAt(col) == '^') {
                adj[col] += -beamPathsCounts[col];
                adj[col - 1] += beamPathsCounts[col];
                adj[col + 1] += beamPathsCounts[col];
                adjusted = true;
            }
        }
        if (adjusted) {
            for (int col = 0; col < length; col++) {
                beamPathsCounts[col] += adj[col];
            }
        }
    }


}
