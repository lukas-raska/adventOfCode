package year_2015.day_13;

import utils.MathUtils;

import java.util.List;

public class TableManager {

    private Table table;

    public TableManager(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public int countMutualSympathies(List<Person> personList) {

        int total = 0;


        for (int i = 0; i < personList.size(); i++) {
            Person first = personList.get(i);
            Person next = personList.get((i + 1) % personList.size());
            total += first.getSympathyMap().get(next.getName());
            total += next.getSympathyMap().get(first.getName());
        }
        return total;
    }

    public int countOptimalHappiness() {

        List<List<Person>> allPermutations = MathUtils.allPermutations(this.table.getPersonList());
        int maxHappiness = Integer.MIN_VALUE;

        for (var personList : allPermutations) {
            int happiness = countMutualSympathies(personList);
            if (happiness > maxHappiness) {
                maxHappiness = happiness;
            }
        }

        return maxHappiness;
    }
}
