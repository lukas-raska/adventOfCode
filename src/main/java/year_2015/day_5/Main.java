package year_2015.day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static final String[] TEST_DATA_1 = {"ugknbfddgicrmopn", "jchzalrnumimnmhp", "haegwjzuvuyypxyu",
            "dvszwmarrgswjxmb"};
    private static final String[] TEST_DATA_2 = {"qjhvhtzxzqqjkmpb", "xxyxx", "uurcxstgmygtbstg",
            "ieodomkazucvgmuy"};

    public static void main(String[] args) throws FileNotFoundException {

        SantaStringsChecker checker = new SantaStringsChecker();


        //testing
        for (String data : TEST_DATA_1) {
            System.out.println(data + " is nice? : " + checker.isNice(data,1));
        }
        System.out.println();
        for (String data : TEST_DATA_2) {
            System.out.println(data + " is nice? : " + checker.isNice(data,2));
        }

        File input = new File("src/main/resources/2015/input_2015_5.txt");
        Scanner scanner = new Scanner(input);

        int counter = 0;
        int counter2 = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (checker.isNice(line,1)){
                counter++;
            }
            if (checker.isNice(line,2)){
                counter2++;
            }
        }

        System.out.println("Answer of day 5: ");
        System.out.println("Part one: " + counter);
        System.out.println("Part two: " + counter2);

    }
}
