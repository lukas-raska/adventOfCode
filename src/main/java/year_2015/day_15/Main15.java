package year_2015.day_15;

import utils.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Main15 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2015,15, "puzzle_input_2015_15.txt");

        List<Ingredients> ingredients = puzzleInput.stream()
                .map(Ingredients::parse)
                .collect(Collectors.toList());

                int result = new Recipe(ingredients).getBestValuedCombination();

        System.out.println("Answer of the day 15:");
        System.out.println("Part 1: " + result); //222870

    }
}
