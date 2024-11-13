package year_2015.day_13;

import java.util.HashMap;
import java.util.Map;

public class Person {

    private final String name;

    private final Map<String,Integer> sympathyMap;

    public Person(String name){
        this.name = name;
        this.sympathyMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSympathyMap() {
        return sympathyMap;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sympathyMap=" + sympathyMap +
                '}';
    }
}
