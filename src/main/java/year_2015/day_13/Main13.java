package year_2015.day_13;

import utils.InputLoader;

public class Main13 {

    public static void main(String[] args) {

        var puzzleInput = InputLoader.load(2015, 13);

        TableManager tableManager = new TableManager(new Table(puzzleInput));

        int resultPart1 = tableManager.countOptimalHappiness();

        //part 2
        Person myself = new Person("Me");
        tableManager
                .getTable()
                .getPersonList()
                .stream()
                .forEach(person -> {
                    myself.getSympathyMap().put(person.getName(), 0);
                    person.getSympathyMap().put("Me", 0);
                });
        tableManager
                .getTable()
                .getPersonList()
                .add(myself);

        int resultPart2 = tableManager.countOptimalHappiness();

        System.out.println("The answer of the day 13: ");
        System.out.println("Part 1: " + resultPart1);
        System.out.println("Part 2: " + resultPart2);

    }


}
