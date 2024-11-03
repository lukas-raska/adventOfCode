package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputLoader {

    public static List<String> load(int year,
                                    int day,
                                    String fileName) {

        Path filePath = Path.of("src/main/java/year_" + year + "/day_" + day + "/" + fileName);

        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
