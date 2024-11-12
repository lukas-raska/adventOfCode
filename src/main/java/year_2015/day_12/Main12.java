package year_2015.day_12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Main12 {

    public static void main(String[] args) throws JsonProcessingException {

        List<String> input = InputLoader.load(2015, 12, "input_2015_12.txt");
        String inputString = input.stream().collect(Collectors.joining(" "));

        //using Jackson library to parse input string to JSON nodes
        var rootNode = new ObjectMapper().readTree(inputString);

        //part 1
        int part1Sum = JsonCalculator.calculateSum(rootNode);
        //part 2
        int part2Sum = JsonCalculator.calculateSum(rootNode, "red");

        System.out.println("Answer of the day 12:");
        System.out.println("Part 1: " + part1Sum); //111754
        System.out.println("Part 2: " + part2Sum); //65402

    }


}



