package year_2015.day_15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

    private final static int MAX_AMOUNT = 100;
    List<Ingredients> ingredients;

    public Recipe(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public int getValueOfCombinationWithGivenCalories(int neededCalories) {

        return findAllCombinationsOfIngredientsByChatGPT().stream()
                .filter(map -> countCaloriesOfCombination(map) == neededCalories)
                .mapToInt(this::countValueOfCombination)
                .max()
                .orElseThrow();
    }

    public int getBestValuedCombination() {
        return findAllCombinationsOfIngredientsByChatGPT().stream()
                .mapToInt(this::countValueOfCombination)
                .max()
                .orElseThrow();
    }

    public int countCaloriesOfCombination(Map<Ingredients, Integer> combination) {
        return combination.entrySet().stream()
                .mapToInt(entry -> entry.getKey().calories() * entry.getValue())
                .sum();
    }

    public int countValueOfCombination(Map<Ingredients, Integer> combination) {

        int totalCapacity = 0, totalDurability = 0, totalFlavor = 0, totalTexture = 0;

        for (var entry : combination.entrySet()) {
            Ingredients ingredients = entry.getKey();
            int amount = entry.getValue();
            totalCapacity += (ingredients.capacity() * amount);
            totalDurability += (ingredients.durability() * amount);
            totalFlavor += (ingredients.flavor() * amount);
            totalTexture += (ingredients.texture() * amount);
        }

        return Math.max(totalCapacity, 0) * Math.max(totalDurability, 0) * Math.max(totalFlavor, 0) * Math.max(totalTexture, 0);
    }

    public List<Map<Ingredients, Integer>> findAllCombinationsOfIngredientsByChatGPT() {
        List<Map<Ingredients, Integer>> combinations = new ArrayList<>();
        generateCombinations(new HashMap<>(), 0, MAX_AMOUNT, combinations);
        return combinations;
    }

    private void generateCombinations(Map<Ingredients, Integer> current,
                                      int index,
                                      int remainingAmount,
                                      List<Map<Ingredients, Integer>> results) {

        Ingredients currentIngredients = this.ingredients.get(index);

        if (index == this.ingredients.size() - 1) {
            current.put(currentIngredients, remainingAmount);
            results.add(new HashMap<>(current));
            return;
        }

        for (int amount = 1; amount < remainingAmount; amount++) {
            current.put(currentIngredients, amount);
            generateCombinations(current, index + 1, remainingAmount - amount, results);
            current.remove(currentIngredients);
        }

    }
}
