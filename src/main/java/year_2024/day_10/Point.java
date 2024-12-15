package year_2024.day_10;

public class Point {

    private int row;
    private int col;
    private int height;
    private boolean reached ;

    public Point(int row,
                 int col,
                 int height) {
        this.row = row;
        this.col = col;
        this.height = height;
        this.reached = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHeight() {
        return height;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
