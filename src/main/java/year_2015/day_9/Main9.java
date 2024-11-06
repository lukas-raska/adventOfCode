package year_2015.day_9;

import utils.InputLoader;

import java.util.List;


public class Main9 {


    public static void main(String[] args) {

        String inputFileName = "input_2015_day9.txt";
        String testDataFile = "testData_day9.txt";

        List<String> inputData = InputLoader.load(2015, 9, inputFileName);

        RouteManager santasRouteManager = new RouteManager(inputData);
        int minDistance =  santasRouteManager.getShortestDistanceThroughAllTowns();

        System.out.println("Answer of the day 9: ");
        System.out.println("Part 1: " + minDistance);


    }


}
