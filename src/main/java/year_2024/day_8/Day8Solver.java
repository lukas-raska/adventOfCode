package year_2024.day_8;

import java.util.*;

public class Day8Solver {

    private char[][] antennaMap;

    public boolean[][] antinodes;

    public Day8Solver(List<String> puzzleInput) {
        this.antennaMap = new char[puzzleInput.size()][puzzleInput.getFirst().length()];
        for (int r = 0; r < puzzleInput.size(); r++) {
            for (int c = 0; c < puzzleInput.getFirst().length(); c++) {
                char current = puzzleInput.get(r).charAt(c);
                if (Character.isDigit(current) || Character.isLetter(current)) {
                    this.antennaMap[r][c] = current;
                }

            }
        }
        this.antinodes = new boolean[puzzleInput.size()][puzzleInput.getFirst().length()];
    }

    public int solvePart2() {
        int count = 0;
        String possible = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        var locations = getAntennasLocations();

        for (char currentAntenna : possible.toCharArray()) {
            if (locations.containsKey(currentAntenna) && locations.get(currentAntenna).size() > 1) {
                var antennaList = locations.get(currentAntenna);
                for (int i = 0; i < antennaList.size() - 1; i++) {
                    Integer[] firstAnt = antennaList.get(i);
                    for (int j = i + 1; j < antennaList.size(); j++) {
                        Integer[] secondAnt = antennaList.get(j);
                        placeAntinodesPart2(firstAnt, secondAnt);
                        //  visualize();
                        //  System.out.println();
                    }
                }
            }
        }
        for (var row : this.antinodes) {
            for (var b : row) {
                count += b ? 1 : 0;
            }
        }
        return count;
    }

    public void placeAntinodesPart2(Integer[] first,
                                    Integer[] second) {
        int rowDiff = (second[0] - first[0]);
        int colDiff = (second[1] - first[1]);

        //first diagonal
        int row = first[0];
        int col = first[1];
        while (row - rowDiff >= 0 && row - rowDiff < this.antinodes.length && col - colDiff >= 0 && col - colDiff < this.antinodes[0].length) {
            row -= rowDiff;
            col -= colDiff;
            this.antinodes[row][col] = true;
        }

        //second diagnoal
        row = second[0];
        col = second[1];
        while (row + rowDiff >= 0 && row + rowDiff < this.antinodes.length && col + colDiff >= 0 && col + colDiff < this.antinodes[0].length) {
            row += rowDiff;
            col += colDiff;
            this.antinodes[row][col] = true;
        }

        this.antinodes[first[0]][first[1]] = true;
        this.antinodes[second[0]][second[1]] = true;


    }

    public int solvePart1() {
        int count = 0;
        String possible = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        var locations = getAntennasLocations();

        for (char currentAntenna : possible.toCharArray()) {
            if (locations.containsKey(currentAntenna) && locations.get(currentAntenna).size() > 1) {
                var antennaList = locations.get(currentAntenna);
                for (int i = 0; i < antennaList.size() - 1; i++) {
                    Integer[] firstAnt = antennaList.get(i);
                    for (int j = i + 1; j < antennaList.size(); j++) {
                        Integer[] secondAnt = antennaList.get(j);
                        placeAntinodes(firstAnt, secondAnt);
                        //  visualize();
                        //  System.out.println();
                    }
                }
            }
        }
        for (var row : this.antinodes) {
            for (var b : row) {
                count += b ? 1 : 0;
            }
        }

        return count;
    }

    public void placeAntinodes(Integer[] first,
                               Integer[] second) {
        int rowDiff = (second[0] - first[0]);
        int colDiff = (second[1] - first[1]);
        //antinode1
        int row = first[0] - rowDiff;
        int col = first[1] - colDiff;
        //int [] antinode1 =
        if (row >= 0 && row < this.antinodes.length && col >= 0 && col < this.antinodes[0].length)
            this.antinodes[row][col] = true;

        //antinode2
        int row2 = second[0] + rowDiff;
        int col2 = second[1] + colDiff;
        //int [] antinode1 =
        if (row2 >= 0 && row2 < this.antinodes.length && col2 >= 0 && col2 < this.antinodes[0].length)
            this.antinodes[row2][col2] = true;
    }


    public Map<Character, List<Integer[]>> getAntennasLocations() {
        Map<Character, List<Integer[]>> map = new HashMap<>();
        for (int r = 0; r < this.antennaMap.length; r++) {
            for (int c = 0; c < this.antennaMap[r].length; c++) {
                char currentAntena = this.antennaMap[r][c];
                Integer[] coordinates = new Integer[]{r, c};
                if (!map.containsKey(currentAntena)) {
                    List<Integer[]> cordinatesList = new ArrayList<>();
                    cordinatesList.add(coordinates);
                    map.put(currentAntena, cordinatesList);
                } else {
                    map.get(currentAntena).add(coordinates);
                }
            }
        }
        return map;
    }

    public void visualize() {
        for (int r = 0; r < this.antennaMap.length; r++) {
            for (int c = 0; c < this.antennaMap[0].length; c++) {
                char printable = (this.antinodes[r][c]) ? '#' : this.antennaMap[r][c];
                System.out.print(printable);
            }
            System.out.println();
        }
    }
}
