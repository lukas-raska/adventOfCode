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

    //part two methods
    private boolean containsPair(String string) {

        if (string.length() >= 4) {

            for (int i = 0; i < string.length() - 3; i++) {
                String tested = string.substring(i, i + 2);
                String rest = string.substring(i + 2);
                if (rest.contains(tested)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasRepeatedLetter(String string) {

        if (string.length() >= 3) {
            for (int i = 0; i < string.length() - 2; i++) {
                String tested = string.substring(i, i + 3);
                if (tested.charAt(0) == tested.charAt(2)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isNice(String string,
                   int level) {

        return switch (level) {
            case 1 -> containsAtLeastThreeVowels(string) &&
                    containsDoubledLetter(string) &&
                    doesNotContainsExcludedStrings(string);
            case 2 ->   hasRepeatedLetter(string) && containsPair(string);
            default -> throw new IllegalArgumentException("Unrecognized level: " + level);
        };
    }
}
