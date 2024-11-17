package year_2015.day_16;

import java.util.HashMap;
import java.util.Map;

public record AuntSue(int id,
                      Map<String, Integer> clues) {

    public static AuntSue parse(String data) {

        Map<String, Integer> result = new HashMap<>();

        int id = Integer.parseInt(data.substring(data.indexOf(" "), data.indexOf(":")).trim());
        String[] cluesData = data.substring(data.indexOf(":")+1).split(",");
        for (String rec : cluesData) {
            var splitRec = rec.split(":");
            String key = splitRec[0].trim();
            int value = Integer.parseInt(splitRec[1].trim());
            result.put(key, value);
        }
        return new AuntSue(id,result);
    }
}
