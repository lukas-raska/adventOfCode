package year_2024.day_10;

public enum Compass {

    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int dCol;
    private final int dRow;

    Compass(int dCol,
            int dRow) {
        this.dCol = dCol;
        this.dRow = dRow;
    }

    public int getdCol() {
        return dCol;
    }

    public int getdRow() {
        return dRow;
    }
}
