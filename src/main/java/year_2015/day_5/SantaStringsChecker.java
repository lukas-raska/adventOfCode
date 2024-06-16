package year_2015.day_5;

public class SantaStringsChecker {

    private static final String VOWELS = "aeiou";
    private static final String[] EXCLUDED_STRINGS = new String[]{"ab", "cd", "pq", "xy"};

    private boolean containsAtLeastThreeVowels(String string) {

        long vowelsCount = string
                .chars()
                .filter(c -> VOWELS.indexOf(c) >= 0)
                .count();

        return vowelsCount >= 3;
    }

    private boolean containsDoubledLetter(String string) {

        for (int i = 0; i < string.length() - 1; i++) {
            String tested = string.substring(i, i + 2);
            if (tested.charAt(0) == tested.charAt(1)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsGivenStrings(String string,
                                         String[] comparisons) {
        for (String comparison : comparisons) {
            if (string.contains(comparison)) {
                return true;
            }
        }
        return false;
    }

    boolean doesNotContainsExcludedStrings(String string) {
        return !containsGivenStrings(string, EXCLUDED_STRINGS);
    }

    boolean isNice(String string,
                   int level) {

            return switch (level) {
                case 1 -> containsAtLeastThreeVowels(string) &&
                        containsDoubledLetter(string) &&
                        doesNotContainsExcludedStrings(string);
                case 2 -> false;
                default -> throw new IllegalArgumentException("Unrecognized level: " + level);
            };
    }
}
