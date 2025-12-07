package year_2015.day_19;

import common.utils.InputLoader;

import java.util.List;

public class Main19 {

    public static void main(String[] args) {

        List<String> puzzleInput = InputLoader.load(2015,19);
        String last = puzzleInput.removeLast();
        System.out.println(last);


    }
}
