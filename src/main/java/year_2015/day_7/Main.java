package year_2015.day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static String SOURCE_PATH = "src/main/java/year_2015/day_7/inputData.txt";
    private final static String TEST_SOURCE_PATH = "src/main/java/year_2015/day_7/testInput.txt";

    public static void main(String[] args) {

        //načtení dat ze souboru
        List<String> logicGates = new ArrayList<>();
        try {
            logicGates = Files.readAllLines(Path.of(SOURCE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Circuit bobbysCircuit = new Circuit(logicGates);
        String searchedWire = "a";
        int searchedWireSignal = bobbysCircuit.getWires().get(searchedWire);

        System.out.println("Answer of the day 7:");
        System.out.println("Part 1: " + searchedWireSignal); //956


        //PART2: modifikace vstupu - zjištěnou hodnotu signálu na vodiči "a" použít jako vstupní hodnotu pro vodič
        // "b" a přepočítat
        bobbysCircuit.reset();
        List<String> modifiedLogicGates = new ArrayList<>(logicGates);


        //všechny 'b' nahradím hodnotou 'a' z part 1
        for (int i = 0; i < logicGates.size(); i++) {
            Pattern bPattern = Pattern.compile("\\b(b)\\b");
            Matcher bMatcher = bPattern.matcher(modifiedLogicGates.get(i));
            if (bMatcher.find()) {
                String[] splitGate = modifiedLogicGates.get(i).split("->");
                modifiedLogicGates.set(
                        i,
                        splitGate[0].replaceAll(
                                "(?<!\\\\w)b(?!\\\\w)",
                                String.valueOf(searchedWireSignal)) + " -> " + splitGate[1]);
            }

        }

        //a přepočítám
        bobbysCircuit.computeSignals(modifiedLogicGates);
        int searchedWireSignalP2 = bobbysCircuit.getWires().get(searchedWire);
        System.out.println("Part 2: " + searchedWireSignalP2);



    }
}
