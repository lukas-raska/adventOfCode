package year_2015.day_18;

import java.util.List;
import java.util.Map;


public class ChristmasLights {

    private Light[][] lights;

    public ChristmasLights(List<String> lightsData) {
        this.lights = parseLights(lightsData);
    }

    public boolean isNeighbourOn(Light current,
                                 Direction neighbourDirection) {
        int column = current.getColumn();
        int row = current.getRow();

        Map<Direction, int[]> directionOffsets = Map.of(
                Direction.ABOVE, new int[]{0, -1}, //{col, row}
                Direction.ABOVE_LEFT, new int[]{-1, -1},
                Direction.ABOVE_RIGHT, new int[]{1, -1},
                Direction.UNDER, new int[]{0, 1},
                Direction.UNDER_LEFT, new int[]{-1, 1},
                Direction.UNDER_RIGHT, new int[]{1, 1},
                Direction.LEFT, new int[]{-1, 0},
                Direction.RIGHT, new int[]{1, 0}
        );

        int[] currentNeighbourOffset = directionOffsets.get(neighbourDirection);

        int neighbourRow = row + currentNeighbourOffset[1];
        int neighbourColumn = column + currentNeighbourOffset[0];

        if (neighbourRow < 0 || neighbourRow >= this.lights.length || neighbourColumn < 0 || neighbourColumn >= this.lights[0].length) {
            return false;
        }

        return this.lights[neighbourColumn][neighbourRow].isOn();
    }

    public int countNeighboursOn(Light light) {

        int neighboursOn = 0;
        for (var dir : Direction.values()) {
            if (isNeighbourOn(light, dir)) {
                neighboursOn++;
            }
        }
        return neighboursOn;
    }


    public void toggleLights(boolean part2) {

        for (int row = 0; row < this.lights.length; row++) {
            for (int column = 0; column < this.lights[row].length; column++) {
                Light current = this.lights[column][row];
                if (part2 && isInCorner(current) && !current.isOn()) {
                    current.toggle();
                }
                int neighboursOn = countNeighboursOn(current);
                current.setWillBeSwitched(neighboursOn);
            }
        }

        for (int row = 0; row < this.lights.length; row++) {
            for (int column = 0; column < this.lights[row].length; column++) {
                Light current = this.lights[column][row];

                if (current.willBeSwitched()) {
                    current.toggle();
                }
                if (part2 && isInCorner(current) && !current.isOn()) {
                    current.toggle();
                }
            }
        }
    }

    public Light[][] getLights() {
        return lights;
    }

    private boolean isInCorner(Light light) {
        int column = light.getColumn();
        int row = light.getRow();
        int maxRow = this.lights.length - 1;
        int maxColumn = this.lights[0].length - 1;
        return column == 0 && row == 0 ||
                column == maxColumn && row == 0 ||
                column == maxColumn && row == maxRow ||
                column == 0 && row == maxRow;
    }

    public void resetForPart2(List<String> lightsData) {
        this.lights = parseLights(lightsData);
        int maxRow = this.lights.length-1;
        int maxColumn = this.lights[0].length-1;

        lights[0][0].setOn(true);
        lights[0][maxColumn].setOn(true);
        lights[maxRow][0].setOn(true);
        lights[maxRow][maxColumn].setOn(true);

    }

    public static Light[][] parseLights(List<String> lightsData) {

        int rows = lightsData.size();
        int columns = lightsData.get(0).length();

        var lights = new Light[columns][rows];
        for (int row = 0; row < columns; row++) {
            for (int column = 0; column < columns; column++) {
                char currentMark = lightsData.get(row).charAt(column);
                lights[column][row] = new Light(column, row, currentMark);
            }
        }
        return lights;
    }

    public void printLights() {
        for (int row = 0; row < this.lights.length; row++) {
            for (int column = 0; column < this.lights[row].length; column++) {
                System.out.print(this.lights[column][row].isOn() ? "#" : ".");
            }
            System.out.println();
        }
    }


}
