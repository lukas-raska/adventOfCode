package year_2024.day_5;

import java.util.Arrays;
import java.util.List;

public record UpdateOrder(int... pages) {

    public boolean contains(int number) {
        return Arrays.stream(this.pages).anyMatch(page -> page == number);
    }

    public int indexOf(int number) {
        for (int i = 0; i < this.pages.length; i++) {
            if (this.pages[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValid(List<PageOrderingRule> rules) {
        for (var rule : rules) {
            int previous = rule.previous();
            int next = rule.next();
            if (this.contains(previous) && this.contains(next)) {
                int indexOfPrevious = this.indexOf(previous);
                int indexOfNext = this.indexOf(next);
                if (indexOfPrevious > indexOfNext) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public String toString() {
        return "UpdateOrder{" +
                "pages=" + Arrays.toString(pages) +
                '}';
    }

    public int getMiddlePage() {
        return this.pages[pages.length / 2];
    }

    public static UpdateOrder parse(String data) {
        int[] parsed = Arrays.stream(data.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new UpdateOrder(parsed);
    }
}
