package year_2015.day_18;

import utils.InputLoader;

import java.util.Arrays;

public class Main18 {

    public static void main(String[] args) {

        var input = InputLoader.load(2015, 18, "puzzle_input_2015_18.txt");
        int steps = 100;

        //part 1
        ChristmasLights christmasLights = new ChristmasLights(input);

        for (int i = 0; i < steps; i++) {
            christmasLights.toggleLights(false);
        }

        var totalLightOn = Arrays.stream(christmasLights.getLights())
                .flatMap(Arrays::stream)
                .filter(Light::isOn)
                .count();

        //part 2
        christmasLights.resetForPart2(input);

        for (int i = 0; i < steps; i++) {
            christmasLights.toggleLights(true);
        }

        var totalLightOnPart2 = Arrays.stream(christmasLights.getLights())
                .flatMap(Arrays::stream)
                .filter(Light::isOn)
                .count();


        System.out.println("The answer of the day 18: ");
        System.out.println("Part 1: " + totalLightOn); //814
        System.out.println("Part 2: " + totalLightOnPart2); //924


    }
}
