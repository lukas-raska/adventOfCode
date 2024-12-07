package year_2024.day_6;

import java.util.Arrays;

public enum Direction {

    LEFT(-1, 0, '<'),
    UP(0, -1, '^'),
    RIGHT(1, 0, '>'),
    DOWN(0, 1, 'v');

    private int dx;
    private int dy;
    private char icon;


    Direction(int dx,
              int dy,
              char icon) {
        this.dx = dx;
        this.dy = dy;
        this.icon = icon;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public char getIcon() {
        return icon;
    }

    public static Character[] getPossibleIcons() {
        return Arrays.stream(Direction.values())
                .map(Direction::getIcon)
                .toArray(Character[]::new);
    }
}
