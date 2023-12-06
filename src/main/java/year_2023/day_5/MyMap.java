package year_2023.day_5;

import java.util.StringTokenizer;

public class MyMap {
    private long destinationRangeStart;
    private long sourceRangeStart;
    private long sourceRangeEnd;
    private long rangeLength;

    public MyMap(String inputData) {
        String[] splitted = inputData.trim().split(" ");
        long[] splittedAndParsed = new long[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            splittedAndParsed[i] = Long.parseLong(splitted[i].trim());
        }
        this.destinationRangeStart = splittedAndParsed[0];
        this.sourceRangeStart = splittedAndParsed[1];
        this.rangeLength = splittedAndParsed[2];
        this.sourceRangeEnd = getSourceRangeEnd();
    }

    public long getDestinationRangeStart() {
        return destinationRangeStart;
    }

    public long getSourceRangeStart() {
        return sourceRangeStart;
    }

    public long getSourceRangeEnd() {
        return sourceRangeStart + rangeLength;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.destinationRangeStart, this.sourceRangeStart, this.rangeLength);
    }
}
