package year_2024.day_5;

import java.util.Arrays;

public record PageOrderingRule(int previous, int next) {
    public static PageOrderingRule parse  (String data){
        int [] parsed = Arrays.stream(data.split("\\|"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new PageOrderingRule(parsed[0], parsed[1]);

    }
}
