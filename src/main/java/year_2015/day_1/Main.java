package year_2015.day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static final Path relativePath = Paths.get("src", "main", "java", "year_2015", "day_1","input.txt");
    public static void main(String[] args) {

        int floorLevel = 0;
        int numberOfStepsToBasement = 0;

         try {
             List<String> fetchedLines = Files.readAllLines(relativePath.toAbsolutePath());
             for (String line: fetchedLines){
                 floorLevel+=countFloor(line);
                 numberOfStepsToBasement = findNumberOfStepsToBasement(line);
             }

         }catch (IOException exception){
            System.err.println("File reading error: " + exception.getMessage());
        }

        System.out.println("The answer of Day 1:");
        System.out.println("Part one: " + floorLevel);
        System.out.println("Part two: " + numberOfStepsToBasement);
    }

    public static  int countFloor (String input){
        int floor = 0;
        for (char c:input.toCharArray()){
            if (c == '('){
                floor++;
            }
            if (c==')'){
                floor--;
            }
        }
        return floor;
    }

    public static int findNumberOfStepsToBasement (String input){
        int steps = 0;
        int floor = 0;
        for (char c: input.toCharArray()){
            if (c=='('){
                floor++;
            }
            if (c==')'){
                floor--;
            }
            steps++;
            if (floor==-1){
                return steps;
            }
        }
        return 0;


    }
}
