package common;

public interface Solver<T1,T2> {

     T1 part1();

     T2 part2();

    default  void printSolution() {
        long start = System.nanoTime();
        T1 result1 = part1();
        long elapsedMillis = Math.round((System.nanoTime() - start) / 1_000_000.);
        System.out.println("Part 1: " + result1 + " (elapsed: " + elapsedMillis + " ms)");
        start = System.nanoTime();
        T2 result2 = part2();
        elapsedMillis = Math.round((System.nanoTime() - start) / 1_000_000.);
        System.out.println("Part 2: " + result2 + " (elapsed: " + elapsedMillis + " ms)");
    }


}
