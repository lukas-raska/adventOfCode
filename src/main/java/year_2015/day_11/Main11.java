package year_2015.day_11;

public class Main11 {

    public static void main(String[] args) {

        String puzzleInput = "hxbxwxba";

        PasswordGenerator generator = new PasswordGenerator();

        String newPasswordPart1 = generator.generateNewPassword(puzzleInput);
        String newPasswordPart2 = generator.generateNewPassword(newPasswordPart1);

        System.out.println("Answer of the day 11:");
        System.out.println("Part 1: " + newPasswordPart1);
        System.out.println("Part 2: " + newPasswordPart2);



    }
}
