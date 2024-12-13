package year_2024.day_9;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Day9Solver {

    private final String diskMap;

    public Day9Solver(String puzzleInput) {
        this.diskMap = puzzleInput;
    }

    public long solvePart1() {
        var sorted = sortFileSystem();
        var compacted = compactFileSystem(sorted);
        return checkSum(compacted);
    }


    public void printList(List<Integer> list) {
        for (var n : list) {
            System.out.print((n == -1) ? "." : n);
        }
        System.out.println();
    }

    private List<Integer> sortFileSystem() {
        int id = 0;
        List<Integer> fileSystem = new LinkedList<>();

        for (int i = 0; i < this.diskMap.length(); i++) {
            if (i % 2 == 0) {
                int files = valueAt(i);
                for (int j = 0; j < files; j++) {
                    fileSystem.add(id);
                }
                id++;
            } else {
                int freeSpace = valueAt(i);
                for (int j = 0; j < freeSpace; j++) {
                    fileSystem.add(-1);
                }
            }
        }
//        int controlCheckSum = fileSystem.stream().mapToInt(Integer::intValue).sum();
//        long countOfMinusOnes = fileSystem.stream().filter(n -> n == -1).count();
//        long countOfNonMinusOnes
//                = fileSystem.stream().filter(n -> n != -1).count();
//        System.out.println("SortFileSystem method: ");
//        System.out.println("Control checksum: " + controlCheckSum);
//        System.out.println("Number of -1: " + countOfMinusOnes);
//        System.out.println("Number of non -1: " + countOfNonMinusOnes);


        return fileSystem;
    }


    private List<Integer> compactFileSystem(List<Integer> sortedFileSystem) {

        int leftPointer = 0;
        int rightPointer = sortedFileSystem.size() - 1;
        while (leftPointer < rightPointer) {
            if (sortedFileSystem.get(leftPointer) == -1) {
                while (sortedFileSystem.get(rightPointer) == -1) {
                    rightPointer--;
                }
                Collections.swap(sortedFileSystem, leftPointer, rightPointer);
                rightPointer--;

            }
            leftPointer++;
        }

//        int controlCheckSum = sortedFileSystem.stream().mapToInt(Integer::intValue).sum();
//        long numberOfMinusOnes = sortedFileSystem.stream().filter(n -> n == -1).count();
//        long numberOfNonMinusOnes = sortedFileSystem.stream().filter(n -> n != -1).count();
//        System.out.println("compactFileSystem method: ");
//        System.out.println("Control checksum: " + controlCheckSum);
//        System.out.println("Number of -1: " + numberOfMinusOnes);
//        System.out.println("Number of non -1: " + numberOfNonMinusOnes);

        return sortedFileSystem;
    }

    private long checkSum(List<Integer> compacted) {
        long checkSum = 0;
        int pos = 0;
        while (pos <= compacted.size() - 1 && compacted.get(pos) != -1) {
            checkSum += (long) pos * compacted.get(pos++);
        }
        return checkSum;
    }

    private int valueAt(int index) {
        return Character.getNumericValue(this.diskMap.charAt(index));
    }

    public String getDiskMap() {
        return diskMap;
    }
}
