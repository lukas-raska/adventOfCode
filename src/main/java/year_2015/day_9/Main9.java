package year_2015.day_9;

import common.utils.InputLoader;

import java.util.List;


public class Main9 {


    public static void main(String[] args) {

        List<String> inputData = InputLoader.load(2015, 9);

        RouteManager santasRouteManager = new RouteManager(inputData);
        int minDistance =  santasRouteManager.getShortestDistanceThroughAllTowns();
        int maxDistance = santasRouteManager.getLongestDistanceThroughAllTowns();

        System.out.println("Answer of the day 9: ");
        System.out.println("Part 1: " + minDistance);
        System.out.println("Part 2: " + maxDistance);


    }


}
