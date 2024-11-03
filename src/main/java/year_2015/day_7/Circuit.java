package year_2015.day_7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Circuit {

    private Map<String, Integer> wires;

    public Circuit(List<String> logicGates) {
        this.wires = findUsedWires(logicGates);
        computeSignals(logicGates);
    }

    public Map<String, Integer> getWires() {
        return wires;
    }

    private Map<String, Integer> findUsedWires(List<String> logicGates) {

        Map<String, Integer> usedWires = new HashMap();
        for (String gate : logicGates) {
            Matcher wireMatcher = Pattern.compile("[a-z]+").matcher(gate);
            while (wireMatcher.find()) {
                usedWires.putIfAbsent(wireMatcher.group(), null);
            }
        }
        return usedWires;
    }

    public void computeSignals(List<String> logicGates) {

        while (this.wires.containsValue(null)) {
            for (String gate : logicGates) {
                String[] splitGate = gate.split("->");
                String input = splitGate[0];
                String output = splitGate[1].trim();
                var inputSignal = computeInputSignal(input);
                if (inputSignal != null) {
                    this.wires.put(output, inputSignal);
                }
            }
        }
    }

    private Integer computeInputSignal(String input) {
        String[] splitInput = Arrays.stream(input.split(" "))
                .map(String::trim)
                .toArray(String[]::new);

        switch (splitInput.length) {
            case 1 -> { //na vstupu přímo hodnota či signál z jednoho vodiče
                if (splitInput[0].matches("[0-9]+")) {   //buďto přímo číselná hodnota
                    return Integer.parseInt(splitInput[0]);
                } else { //nebo signál z jiného vodiče
                    return this.wires.get(splitInput[0]);
                }
            }
            //délka 2 - na výstup  předávám negaci vstupního signálu
            case 2 -> {
                String wire = splitInput[1];
                Integer signal;
                if (wire.matches("[0-9]+")) {
                    signal = Integer.parseInt(wire);
                } else {
                   signal = this.wires.get(wire);
                }
                if (signal == null){
                    return null;
                }
                return BitwiseUtils.not(signal);
            }

            //délka 3 - možné použití operátorů AND, OR, LSHIFT, RSHIFT
            case 3 -> {
                String bitOperator = splitInput[1];
                switch (bitOperator) {
                    case "AND", "OR" -> {
                        String wire1 = splitInput[0];
                        String wire2 = splitInput[2];
                        Integer signal1, signal2;
                        if (wire1.matches("[0-9]+")) {
                            signal1 = Integer.parseInt(wire1);
                        } else {
                            signal1 = this.wires.get(wire1);
                        }
                        if (wire2.matches("[0-9]+")) {
                            signal2 = Integer.parseInt(wire2);
                        } else {
                            signal2 = this.wires.get(wire2);
                        }

                        if (signal1 == null || signal2 == null) {
                            return null;
                        }
                        return bitOperator.equals("AND") ?
                                BitwiseUtils.and(signal1, signal2) : BitwiseUtils.or(signal1, signal2);
                    }

                    case "LSHIFT", "RSHIFT" -> {
                        String wire = splitInput[0];
                        Integer signal;
                        int shift = Integer.parseInt(splitInput[2]);
                        if (wire.matches("[0-9]+")) {
                            signal = Integer.parseInt(wire);
                        } else {
                            signal = this.wires.get(wire);
                        }
                        if (signal == null) {
                            return null;
                        }
                        return bitOperator.equals("LSHIFT") ?
                                BitwiseUtils.leftShift(signal, shift) : BitwiseUtils.rightShift(signal, shift);
                    }
                    default -> throw new IllegalArgumentException("Invalid data. Unknown operator: " + bitOperator);
                }
            }
        }
        return null;
    }


    public void printWires() {
        this.wires.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public void reset(){
        this.wires.entrySet().stream()
                .forEach(entry-> entry.setValue(null));
    }
}
