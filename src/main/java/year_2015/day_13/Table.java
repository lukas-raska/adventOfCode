package year_2015.day_13;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Table {

    private final List<Person> personList;

    public Table(List<String> inputData) {
        this.personList = new ArrayList<>();
        parseData(inputData);
    }


    private void parseData(List<String> data) {

        List<Person> people = new ArrayList<>();

        for (var line : data) {
            String[] split = line.split(" ");
            if (split.length != 11) {
                throw new IllegalArgumentException("Invalid input data.");
            }
            String name1 = split[0];
            String name2 = split[10].replace(".", "");
            String gainOrLose = split[2];
            int sign = (gainOrLose.equals("lose")) ? -1 : 1;
            int happiness = sign * Integer.parseInt(split[3]);

            addPersonIfNotExists(name1);
            addPersonIfNotExists(name2);
            recordSympathyToNeighbour(name1, name2, happiness);
        }

    }

    private boolean existsByName(String name) {
        return this.personList.stream()
                .anyMatch(person -> person.getName().equals(name));
    }

    private void addPersonIfNotExists(String name) {
        if (!existsByName(name)) {
            this.personList.add(new Person(name));
        }
    }

    private void recordSympathyToNeighbour(String personName,
                                           String neighbourName,
                                           int sympathy) {
        var person = findByName(personName);
        person.ifPresent(value -> value.getSympathyMap().put(neighbourName, sympathy));
    }

    private Optional<Person> findByName(String name) {
        return this.personList.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst();
    }

    public List<Person> getPersonList() {
        return personList;
    }


}
