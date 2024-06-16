package year_2015.day_6;

public class Light {

    private final Point point;
    private boolean turnedOn;

    Light(Point point) {
        this(point.x(), point.y());
    }

    Light(int x,
          int y) {
        this.point = new Point(x, y);
        turnedOn = false;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public boolean isTurnedOff() {
        return !turnedOn;
    }


    public void turnOn() {
        turnedOn = true;
    }

    public void turnOff() {
        turnedOn = false;

    }

    public void toggle() {
        turnedOn = !turnedOn;
    }

    public void process(String action) {
        switch (action) {
            case "turn on" -> turnOn();
            case "turn off" -> turnOff();
            case "toggle" -> toggle();
            default -> throw new RuntimeException("Invalid action: " + action);
        }
    }

    @Override
    public String toString() {
        return isTurnedOn() ? "1" : "0";
    }
}
