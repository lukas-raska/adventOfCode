package year_2015.day_15;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Ingredients(String name,
                          int capacity,
                          int durability,
                          int flavor,
                          int texture,
                          int calories) {

    public static Ingredients parse(String ingredientsData) {

        String name = ingredientsData.substring(0, ingredientsData.indexOf(":"));
        Pattern numPattern = Pattern.compile("-?\\d+");
        Matcher numMatcher = numPattern.matcher(ingredientsData);
        var values = numMatcher
                .results()
                .mapToInt(mr -> Integer.parseInt(mr.group()))
                .toArray();
        if (values.length != 5) {
            throw new IllegalArgumentException("Invalid data. Not found matching number od values.");
        }
        int capacity = values[0];
        int durability = values[1];
        int flavor = values[2];
        int texture = values[3];
        int calories = values[4];

        return new Ingredients(name, capacity, durability, flavor, texture, calories);

    }
}
