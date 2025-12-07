public interface Solver {

    <T> T part1();

    <T> T part2();

    default <T> void printSolution() {
        long start = System.nanoTime();
        T result1 = part1();
        long elapsedMillis = Math.round((System.nanoTime() - start) / 1_000_000.);
        System.out.println("Part 1: " + result1 + " (elapsed: " + elapsedMillis + ")");
        start = System.nanoTime();
        T result2 = part2();
        elapsedMillis = Math.round((System.nanoTime() - start) / 1_000_000.);
        System.out.println("Part 2: " + result2 + " (elapsed: " + elapsedMillis + ")");
    }


}
