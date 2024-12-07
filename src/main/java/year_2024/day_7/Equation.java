package year_2024.day_7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record Equation(long result,
                       long... members) {


    public boolean isValid(boolean isPart2) {
        Set<Long> foundResults = new HashSet<>();
        processMembers(foundResults, this.members()[0], 1, isPart2);
        return foundResults.contains(this.result());
    }

    private void processMembers(Set<Long> foundResults,
                                long currentResult,
                                int indexOfNext,
                                boolean isPart2) {

        if (indexOfNext == this.members().length) {
            foundResults.add(currentResult);
            return;
        }
        long nextMember = this.members()[indexOfNext];
        processMembers(foundResults, currentResult + nextMember, indexOfNext + 1, isPart2);
        processMembers(foundResults, currentResult * nextMember, indexOfNext + 1, isPart2);
        if (isPart2) {
            processMembers(foundResults, concatDigits(currentResult, nextMember), indexOfNext + 1, true);
        }

    }

    public static long concatDigits(long first,
                                    long second) {
        long temp = second;
        while (temp != 0) {
            first *= 10;
            temp /= 10;
        }
        return first + second;
    }

    @Override
    public String toString() {
        return "Equation{" +
                "result=" + result +
                ", members=" + Arrays.toString(members) +
                '}';
    }
}








