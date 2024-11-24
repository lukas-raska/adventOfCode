package year_2015.day_18;

public class Light {

    private int column;
    private int row;

    private boolean on;

    private boolean willBeSwitched;

    public Light(int column,
                 int row,
                 char mark) {
        this.column = column;
        this.row = row;
        this.on = switch (mark) {
            case '#' -> true;
            case '.' -> false;
            default -> throw new IllegalArgumentException("Unknown light mark: " + mark);
        };
    }

    public int getColumn() {
        return column;
    }

    public boolean isOn() {
        return on;
    }

    public int getRow() {
        return row;
    }

    public boolean willBeSwitched() {
        return willBeSwitched;
    }

    public void toggle(){
        on = !on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public void setWillBeSwitched(int  neighboursOn) {
        this.willBeSwitched = this.isOn()?
                (neighboursOn!=3 && neighboursOn !=2) : (neighboursOn == 3);
    }
}
