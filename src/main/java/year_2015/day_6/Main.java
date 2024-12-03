package year_2015.day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ChristmasLights christmasLights = new ChristmasLights();
        File inputFile = new File("src/main/resources/2015/input_2015_6.txt");
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            LightsAction action = LightsAction.of(scanner.nextLine());
            christmasLights.processAction(action);
        }

        System.out.println("Answer of day 6:");
        System.out.println("Part one (turned on lights): " + christmasLights.countTurnedOnLights());
        System.out.println("Part two (total brightness): " + christmasLights.totalBrightness());
    }
}
