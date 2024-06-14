package year_2015.day_3;

import java.util.Objects;

public class Location {

    private int x;
    private int y;

    public Location(int x,
                    int y) {
        this.x = x;
        this.y = y;
    }

    public Location (Location location){
        this.x = location.x;
        this.y = location.y;
    }

    public void moveNorth(){
        this.y++;
    }

    public void moveSouth(){
        this.y--;
    }
    public void moveEast(){
        this.x++;
    }
    public void moveWest(){
        this.x--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
