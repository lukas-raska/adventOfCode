package year_2025.day_11;

import common.Solver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11Solver implements Solver<Integer, Integer> {

    private final Map<String, String[]> connectionsMap;

    private final static String START_DEVICE = "you";
    private final static String TARGET_DEVICE = "out";


    public Day11Solver(List<String> puzzleInput) {

        this.connectionsMap = puzzleInput.stream()
                .map(Connection::parseConnection)
                .collect(Collectors.toMap(Connection::input, Connection::outputs));
    }

    @Override
    public Integer part1() {

        return getPathsCount(START_DEVICE);
    }


    private int getPathsCount(String currentDevice) {

        if (currentDevice.equals(TARGET_DEVICE)) {
            return 1;
        }
        int cnt = 0;
        for (String outputs : connectionsMap.get(currentDevice)) {
            cnt+= getPathsCount(outputs);
        }
        return cnt;
    }

    @Override
    public Integer part2() {
        return 0;
    }
}
