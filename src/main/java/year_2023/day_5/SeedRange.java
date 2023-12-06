package year_2023.day_5;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


/**
 * Slouží pro zpracování hodnot rozsahu semínek pro zadání part two
 */
public class SeedRange {

    private long startOfRange;

    private long length;

    private long endOfRange;

    public SeedRange(long startOfRange, long length) {
        this.startOfRange = startOfRange;
        this.length = length;
        this.endOfRange = getEndOfRange();

    }

    public long getStartOfRange() {
        return startOfRange;
    }

    public long getEndOfRange() {
        return this.startOfRange + this.length;
    }

    @Override
    public String toString() {
        return startOfRange + " " + length;
    }
}
