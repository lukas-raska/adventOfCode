package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputLoader {

    public static List<String> load(int year,
                                    int day) {

        Path filePath = Path.of("src/main/resources/" + year + "/input_" + year + "_" + day + ".txt");

        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
