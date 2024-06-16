package year_2015.day_6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightsAction {

    private final Point from;
    private final Point to;
    private final String action;

    public LightsAction(Point from,
                        Point to,
                        String action) {
        this.from = from;
        this.to = to;
        this.action = action;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public String getAction() {
        return action;
    }

    public static LightsAction of(String data) {

        String regex = "(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid data format");
        }

        String action = matcher.group(1);
        int fromX = Integer.parseInt(matcher.group(2));
        int fromY = Integer.parseInt(matcher.group(3));
        int toX = Integer.parseInt(matcher.group(4));
        int toY = Integer.parseInt(matcher.group(5));

        return new LightsAction(
                new Point(fromX, fromY),
                new Point(toX, toY),
                action);
    }


}
