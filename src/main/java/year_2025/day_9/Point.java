package year_2025.day_9;

public class Point {
    private final int row;
    private final int col;
    private Type type;
    private Orientation orientation;
    private Location location;



    public Point(int row,
                 int col) {
        this(row,col,Type.UNDEFINED);
    }

    public Point(int row,
                 int col,
                 Type type) {
       this(row,col, type, Orientation.UNDEFINED);
    }

    public Point (int row, int col, Type type, Orientation orientation){
        this.row = row;
        this.col = col;
        this.type = type;
        this.orientation = orientation;
        this.location = Location.UNDEFINED;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Location getLocation() {
        return location;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public enum Type {
        CORNER,
        EDGE,
        INNER,
        OUTER,
        UNDEFINED
    }

    public enum Orientation {
        VERTICAL, HORIZONTAL, UNDEFINED
    }

    public enum Location {
        UP, DOWN, LEFT, RIGHT, UNDEFINED
    }

    public boolean isCorner(){
        return this.type.equals(Type.CORNER);
    }

    public boolean isEdge(){
        return this.type.equals(Type.EDGE);
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

    public static Point of(int row, int col){
        return new Point(row, col);
    }
}







