package year_2024.day_8;

import java.util.*;

public class Day8Solver {

    private Map<Character, List<Coordinates>> antennaMap;

    private int rowsMax;

    private int colsMax;

  //  public boolean[][] antinodes;

    public Day8Solver(List<String> puzzleInput) {
        initializeData(puzzleInput);
    }

    public int solvePart1(){
        return solve(true);
    }

    public int solvePart2(){
        return solve(false);
    }


    public int solve(boolean isPart1) {

        boolean[][] antinodes = new boolean[rowsMax][colsMax];

        for (char currentFrequency : this.antennaMap.keySet()) {

            List<Coordinates> antennas = this.antennaMap.get(currentFrequency);

            for (int i = 0; i < antennas.size() - 1; i++) {

                Coordinates first = antennas.get(i);

                for (int j = i + 1; j < antennas.size(); j++) {
                    Coordinates second = antennas.get(j);
                    if (isPart1) {
                        placeAntinodes(first, second, antinodes);
                    } else {
                        placeAntinodesWithResonantHarmonics(first,second,antinodes);
                    }

                }
            }
        }
        return getAntinodeCount(antinodes);
    }

    private boolean isWithinRange(int row,
                                  int col) {
        return row >= 0 && row < this.rowsMax && col >= 0 && col < this.colsMax;
    }

    public void placeAntinodes(Coordinates firstAntenna,
                               Coordinates secondAntenna,
                               boolean[][] antinodes) {
        //antinode1
        int row = 2 * firstAntenna.row() - secondAntenna.row();
        int col = 2 * firstAntenna.col() - secondAntenna.col();
        if (isWithinRange(row, col)) {
            antinodes[row][col] = true;
        }
        //antinode2
        int row2 = 2 * secondAntenna.row() - firstAntenna.row();
        int col2 = 2 * secondAntenna.col() - firstAntenna.col();

        if (isWithinRange(row2, col2)) {
            antinodes[row2][col2] = true;
        }

    }


    public void placeAntinodesWithResonantHarmonics(Coordinates first,
                                                    Coordinates second,
                                                    boolean[][] antinodes) {

        int rowDiff = second.row() - first.row();
        int colDiff = second.col() - first.col();

        //first diagonal
        int row = first.row();
        int col = first.col();
        while (isWithinRange(row, col)) {
            antinodes[row][col] = true;
            row -= rowDiff;
            col -= colDiff;
        }
        //second diagonal
        row = second.row();
        col = second.col();
        while (isWithinRange(row, col)) {
            antinodes[row][col] = true;
            row += rowDiff;
            col += colDiff;
        }
    }

    private int getAntinodeCount(boolean[][] antinodes) {
        int count = 0;
        for (var row : antinodes) {
            for (var foundAntinode : row) {
                count += foundAntinode ? 1 : 0;
            }
        }
        return count;
    }


    public void initializeData(List<String> puzzleInput) {
        this.rowsMax = puzzleInput.size();
        this.colsMax = puzzleInput.getFirst().length();
        this.antennaMap = new HashMap<>();

        for (int r = 0; r < this.rowsMax; r++) {
            for (int c = 0; c < this.colsMax; c++) {
                char current = puzzleInput.get(r).charAt(c);
                if (Character.isLetterOrDigit(current)) {
                    Coordinates coordinates = new Coordinates(r, c);
                    if (this.antennaMap.containsKey(current)) {
                        this.antennaMap.get(current).add(coordinates);
                    } else {
                        this.antennaMap.put(current, new ArrayList<>(List.of(coordinates)));
                    }
                }
            }
        }
    }
}
