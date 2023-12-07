package year_2023.day_6;

public class Main {

    //my input
    public static final int[] totalTime = {56, 97, 78, 75};
    public static final int[] recordDistance = {546, 1927, 1131, 1139};

    public static final long[] racePartTwo = {56977875, 546192711311139L};

    public static void main(String[] args) {
        int allWaysToBeatRecord = 1;
        for (int i = 0; i < totalTime.length; i++) {

            allWaysToBeatRecord *= countWaysToBeatRecord(totalTime[i], recordDistance[i]);
        }

        System.out.println("The answer of the day 6:");
        System.out.println("Part one: " + allWaysToBeatRecord);
        System.out.println("Part two: " + countWaysToBeatRecord(racePartTwo[0], racePartTwo[1]));
    }

    public static long countWaysToBeatRecord(long totalTime, long recordDistance) {
        long possibilityToBreakRecord = 0;
        for (long chargingTime = 0; chargingTime <= totalTime; chargingTime++) {
            if (chargingTime * (totalTime - chargingTime) > recordDistance) {
                possibilityToBreakRecord++;
            }
        }
        return possibilityToBreakRecord;
    }

}
