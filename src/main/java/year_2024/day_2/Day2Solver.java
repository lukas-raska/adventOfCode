package year_2024.day_2;

import java.util.ArrayList;
import java.util.List;

public class Day2Solver {
    private final List<Report> reports;

    public Day2Solver(List<String> puzzleInput) {
        this.reports = puzzleInput.stream()
                .map(Report::new)
                .toList();
    }

    public long solvePart1() {
        return this.reports.stream()
                .filter(Report::isSafe)
                .count();
    }

    public long solvePart2() {
        int safeReports = 0;
        for (Report report : this.reports) {
            if (report.isSafe()) {
                safeReports++;
            } else {
                List<Integer> modifiedLevels = new ArrayList<>(report.levels());
                for (int i = 0; i < report.levels().size(); i++) {
                    int removed = modifiedLevels.remove(i);
                    if (new Report(modifiedLevels).isSafe()) {
                        safeReports++;
                        break;
                    }
                    modifiedLevels.add(i, removed);

                }
            }
        }
        return safeReports;
    }


}



