package year_2015.day_8;

import utils.InputLoader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> inputData = InputLoader.load(2015, 8, "inputData.txt");

        int part1result = inputData.stream()
                .mapToInt(s -> s.length() - removeQuotesAndEscapes(s).length())
                .sum();


        System.out.println("Answer of the day 8: ");
        System.out.println("Part 1: " + part1result);


    }

    static String removeQuotesAndEscapes(String string) {

        String modified = string.substring(1, string.length() - 1); //removes '"' at the first and last place

        modified = modified.replaceAll("\\\\x[0-9A-Fa-f]{2}", "*"); //replacing '\x--'
        modified = modified.replaceAll("\\\\\"", "*"); //replacing "\""
        modified = modified.replaceAll("\\\\\\\\", "*"); //replacing "\\"

        return modified;
    }


}
