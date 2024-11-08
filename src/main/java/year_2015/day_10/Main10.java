package year_2015.day_10;

public class Main10 {

    public static void main(String[] args) {

        String input = "3113322113";

        String part1Result = playLookAndSay(input, 40);
        String part2Result = playLookAndSay(input,50);

        System.out.println("Answer of the day 10: ");
        System.out.println("Part 1: " + part1Result.length()); //329356
        System.out.println("Part 2: " + part2Result.length()); //4666278

    }

    public static String playLookAndSay(String input,
                                        int steps) {

        for (int i = 0; i < steps; i++) {

            StringBuilder nextStep = new StringBuilder();
            char digit = input.charAt(0);
            int sequenceLength = 1;

            for (int j = 1; j < input.length(); j++) {

                if (input.charAt(j) == digit) {
                    sequenceLength++;
                } else {
                    nextStep.append(sequenceLength)
                            .append(digit);
                    digit = input.charAt(j);
                    sequenceLength = 1;
                }
            }
            nextStep.append(sequenceLength)
                    .append(digit);
            input = nextStep.toString();
        }
        return input;
    }
}
