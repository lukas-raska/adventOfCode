package year_2015.day_6;

import java.util.Arrays;
import java.util.function.Predicate;

public class ChristmasLights {

    private static final int COLUMNS = 1000;
    private static final int ROWS = 1000;
    private final Light[][] lights;


    public ChristmasLights() {
        this(ROWS, COLUMNS);
    }

    public ChristmasLights(int columns,
                           int rows) {

        this.lights = new Light[columns][rows];
        for (int y = 0; y < lights.length; y++) {
            for (int x = 0; x < lights[y].length; x++) {
                lights[x][y] = new Light(x, y);
            }
        }
    }


    long countTurnedOnLights() {
        return countLights(Light::isTurnedOn);
    }

    long countTurnedOffLights() {
        return countLights(Light::isTurnedOff);
    }

    private long countLights(Predicate<Light> predicate) {
        return Arrays.stream(this.lights)
                .flatMap(Arrays::stream)
                .filter(predicate)
                .count();
    }



    public void processAction(LightsAction action) {

        Point from = action.getFrom();
        Point to = action.getTo();
        String actionName = action.getAction();

        for (int i = from.y(); i <= to.y(); i++) {
            for (int j = from.x(); j <= to.x(); j++) {
                lights[j][i].process(actionName);
            }
        }
    }


    public int totalBrightness(){
       return Arrays.stream(this.lights)
               .flatMap(Arrays::stream)
               .mapToInt(Light::getBrightness)
               .sum();

    }

    public void print() {
        for (Light[] row : lights) {
            for (Light light : row) {
                System.out.print(light);
            }
            System.out.println();
        }
    }
}



