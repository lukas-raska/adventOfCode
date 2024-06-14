package year_2015.day_3;

import java.util.HashSet;
import java.util.Set;

public class Santa {

    private Location current;


    public Santa() {
        current = new Location(0, 0);
    }

    public void move(char direction) {
        switch (direction) {
            case '^' -> current.moveNorth();
            case 'v' -> current.moveSouth();
            case '<' -> current.moveWest();
            case '>' -> current.moveEast();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public Location getCurrent() {
        return current;
    }

}
