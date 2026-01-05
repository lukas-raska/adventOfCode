package year_2025.day_12;

import common.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12Solver implements Solver<Long, Long> {

    private final List<String[]> presents;
    private final List<Region> regions;

    public Day12Solver(List<String> puzzleInput) {

        this.presents = new ArrayList<>();
        this.regions = new ArrayList<>();

        int idx = 0;
        while (idx < puzzleInput.size()) {
            String line = puzzleInput.get(idx);
            if (line.matches("\\d:")) {
                List<String> presentShape = new ArrayList<>();
                for (int i = idx + 1; i < idx + 4; i++) {
                    presentShape.add(puzzleInput.get(i));
                }
                presents.add(presentShape.toArray(String[]::new));
                idx += 5;
            }
            if (line.matches("\\d+x\\d+: .+")) {
                String[] splitData = line.split(":");
                String[] dimensions = splitData[0].split("x");
                int width = Integer.parseInt(dimensions[0]);
                int length = Integer.parseInt(dimensions[1]);
                int[] presentQty = Arrays.stream(splitData[1].trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                regions.add(new Region(width, length, presentQty));
                idx++;
            }
        }

    }


    @Override
    public Long part1() {

        int[] presentAreas = presents.stream()
                .mapToInt(this::getPresentArea)
                .toArray();

        long validCnt = 0;

        for (Region region : regions) {
            int regionArea = region.width() * region.length();
            int totalAreaOfPresentsInRegion = 0;
            for (int presentIdx = 0; presentIdx < region.presentQty().length; presentIdx++) {
               int currentQty = region.presentQty()[presentIdx];
               totalAreaOfPresentsInRegion+= (currentQty * presentAreas[presentIdx]);
            }
            if (regionArea>totalAreaOfPresentsInRegion){
                validCnt++;
            }
        }
        return validCnt;
    }

    @Override
    public Long part2() {
        return 0L;
    }

    private int getPresentArea(String[] present) {
        int area = 0;
        for (String line : present) {
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    area++;
                }
            }
        }
        return area;
    }
}
