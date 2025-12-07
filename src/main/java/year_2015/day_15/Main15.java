package year_2015.day_15;

import common.utils.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Main15 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2015, 15);

        List<Ingredients> ingredients = puzzleInput.stream()
                .map(Ingredients::parse)
                .collect(Collectors.toList());

        Recipe recipe = new Recipe(ingredients);

        //part 1
        int mostValuedCombination = recipe.getBestValuedCombination();

        //part 2
        int combinationOf500Calories = recipe.getValueOfCombinationWithGivenCalories(500);

        System.out.println("Answer of the day 15:");
        System.out.println("Part 1: " + mostValuedCombination); //222870
        System.out.println("Part 2: " + combinationOf500Calories); //117936

    }
}
