package year_2015.day_11;

public class Main11 {

    public static void main(String[] args) {

        String puzzleInput = "hxbxwxba";


        PasswordGenerator generator = new PasswordGenerator();

        String currentPassword = puzzleInput;

        Thread timeThread = new Thread(() -> {
            var startTime = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1);
                    var elapsedTime = System.currentTimeMillis() - startTime;
                    System.out.println("Time: " + elapsedTime + " miliseconds");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        });


        timeThread.start();
        while (!generator.isValid(currentPassword)) {
            currentPassword = generator.incrementPassword(currentPassword);
        }
        timeThread.interrupt();

        System.out.println("Answer of the day 11:");
        System.out.println("Part 1: " + currentPassword);

    }
}
