package year_2015.day_14;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Reindeer(String name,
                       int speed,
                       int timeToFly,
                       int timeToRest) {

    public static Reindeer parse(String data) {
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(data);

        String name = data.substring(0, data.indexOf(" "));
        var numericData = numberMatcher.results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .toArray();
        int speed = numericData[0];
        int timeToFly = numericData[1];
        int timeToRest = numericData[2];

        return new Reindeer(name, speed, timeToFly, timeToRest);
    }

    public int countDistance(int timeLimit) {

        int flyAndRestTime = timeToFly() + timeToRest();
        int numberOfCycles = timeLimit / flyAndRestTime;
        int timeLeft = timeLimit - (numberOfCycles * flyAndRestTime);

        return (numberOfCycles * timeToFly + Math.min(timeLeft, timeToFly)) * speed;
    }
}

