package year_2025.day_9;

public class Point {
    private final int row;
    private final int col;
    private Type type;
    private Location location;


    public Point(int row,
                 int col) {
        this(row, col, Type.UNDEFINED);
    }


    public Point(int row,
                 int col,
                 Type type) {
        this(row, col, type, Location.UNDEFINED);
    }

    public Point(int row,
                 int col,
                 Type type,
                 Location location) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.location = location;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Location getLocation() {
        return location;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        CORNER,
        VERTICAL_EDGE,
        HORIZONTAL_EDGE,
        UNDEFINED
    }


    public enum Location {
        UP, DOWN, LEFT, RIGHT, UNDEFINED
    }


    public boolean isVerticalEdge() {
        return this.type.equals(Type.VERTICAL_EDGE);
    }

    public boolean isHorizontalEdge() {
        return this.type.equals(Type.HORIZONTAL_EDGE);
    }


    public static Point parse(String csv) {
        String[] split = csv.split(",");
        int row = Integer.parseInt(split[1]);
        int col = Integer.parseInt(split[0]);
        return new Point(row, col);
    }

    @Override
    public final boolean equals(Object object) {
        if (!(object instanceof Point point)) {
            return false;
        }

        return getRow() == point.getRow() && getCol() == point.getCol();
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getCol();
        return result;
    }

    @Override
    public String toString() {
        return "[" + row + ":" + col + "]";
    }
}







