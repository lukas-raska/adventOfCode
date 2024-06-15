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

    boolean containsDoubledLetter(String string) {

         for (int i = 0; i < string.length()-1; i++) {
            String tested = string.substring(i, i+2);
            if (tested.charAt(0)==tested.charAt(1)) {
                return true;
            }
        }
        return false;
    }
}
