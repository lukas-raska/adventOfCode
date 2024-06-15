package year_2015.day_5;

public class SantaStringsChecker {

    private static final String VOWELS = "aeiou";

     boolean containsAtLeastThreeVowels(String string) {

        long vowelsCount = string
                .chars()
                .filter(c -> VOWELS.indexOf(c) >= 0)
                .count();

        return vowelsCount >= 3;
    }
}
