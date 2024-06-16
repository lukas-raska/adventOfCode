package year_2015.day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ChristmasLights christmasLights = new ChristmasLights();
        File inputFile = new File("src/main/java/year_2015/day_6/input.txt");
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            LightsAction action = LightsAction.of(scanner.nextLine());
            christmasLights.changeLights(
                    action.getFrom(),
                    action.getTo(),
                    action.getAction());
        }

        System.out.println("Answer of day 6:");
        System.out.println("Part one: " + christmasLights.countTurnedOnLights());
    }
}
