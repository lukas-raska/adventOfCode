package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathUtils {

    public static <T> List<List<T>> allPermutations(List<T> list) {
        List<List<T>> permutations = new ArrayList<>();
        generatePermutations(list, list.size(), permutations);
        return permutations;
    }

    /**
     * Find all possible permutations of elements order in given collection
     * uses Heap's algorithm (https://en.wikipedia.org/wiki/Heap%27s_algorithm)
     *
     * @param list        given collection
     * @param size         size of given collection
     * @param permutations list for storing permutations
     */
    private static <T> void generatePermutations(List<T> list,
                                                 int size,
                                                 List<List<T>> permutations) {
        if (size == 1) {
            permutations.add(new ArrayList<>(list));
        } else {
            for (int i = 0; i < size; i++) {
                generatePermutations(list, size - 1, permutations);
                int swapIndex = (size % 2 == 1) ? 0 : i; //if the size is odd, swap first and last element, else swap i-th and last
                Collections.swap(list, swapIndex, size - 1);
            }
        }
    }
}
