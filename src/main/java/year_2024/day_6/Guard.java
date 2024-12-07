package year_2024.day_6;

public class Guard {
    private int col;
    private int row;
    private Direction direction;


    public Guard(int col,
                 int row,
                 char directionIcon) {
        this.col = col;
        this.row = row;
        this.direction = switch (directionIcon){
            case '<' -> Direction.LEFT;
            case '>' -> Direction.RIGHT;
            case 'v' -> Direction.DOWN;
            case '^' -> Direction.UP;
            default -> throw new IllegalArgumentException("Unknown guard direction: " + directionIcon);
        };
    }

    public void changeDirection() {
        int newIndex = (this.direction.ordinal() + 1) % Direction.values().length;
        setDirection(Direction.values()[newIndex]);
    }

    public void move() {
        setCol(this.getCol() + this.direction.getDx());
        setRow(this.getRow() + this.direction.getDy());
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void reset(int row, int col, Direction direction){
        setRow(row);
        setCol(col);
        setDirection(direction);
    }

    @Override
    public String toString() {
        return "Guard{" +
                "col=" + col +
                ", row=" + row +
                ", direction=" + direction +
                '}';
    }
}
