package year_2015.day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    private static final String[] TEST_DATA = {"ugknbfddgicrmopn", "jchzalrnumimnmhp", "haegwjzuvuyypxyu",
            "dvszwmarrgswjxmb"};

    public static void main(String[] args) throws FileNotFoundException {

        SantaStringsChecker checker = new SantaStringsChecker();

//        for (String data : TEST_DATA) {
//            System.out.println(data + " is nice? : " + checker.isNice(data));
//        }

        File input = new File("src/main/java/year_2015/day_5/input.txt");
        Scanner scanner = new Scanner(input);

        int counter = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (checker.isNice(line)){
                counter++;
            }
        }

        System.out.println("Answer of day 5: ");
        System.out.println("Part one: " + counter);

    }
}
