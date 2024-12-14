package year_2024.day_9;

import java.util.ArrayList;
import java.util.Collections;
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

    public long solvePart2() {
        var sorted = sortFileSystem();
        int id = getMaxUsedId(sorted);
        while (id > 0) {
            int fileLength = getLengthOfFileWithId(id, sorted);
            int indexOfFreeSpace = indexOfAvailableFreeSpace(fileLength, sorted);
            if (indexOfFreeSpace != -1) {
                moveFileToAvailableFreeSpace(indexOfFreeSpace, id, fileLength, sorted);
            }
            id--;
        }
        return checkSum(sorted);
    }

    public void moveFileToAvailableFreeSpace(int indexOfFreeSpace,
                                             int fileId,
                                             int fileLength,
                                             List<Integer> fileSystem) {
        int indexOfFile = fileSystem.indexOf(fileId);
        for (int i = 0; i < fileLength; i++) {
            if (indexOfFreeSpace < indexOfFile) {
                Collections.swap(fileSystem, i + indexOfFreeSpace, i + indexOfFile);
            }

        }
    }

    public int getMaxUsedId(List<Integer> sortedFileSystem) {
        int index = sortedFileSystem.size() - 1;
        int maxId = 0;
        while (index >= 0 || sortedFileSystem.getLast() == -1) {
            maxId = sortedFileSystem.getLast();
            index--;
        }
        return maxId;
    }

    public int indexOfAvailableFreeSpace(int fileLength,
                                         List<Integer> fileSystem) {
        int freeSpaceCounter = 0;
        int indexOfFreeSpace = -1;
        boolean freeSpaceFound = false;
        for (int i = 0; i < fileSystem.size() - fileLength; i++) {
            if (fileSystem.get(i) == -1) {
                if (!freeSpaceFound) {
                    indexOfFreeSpace = i;
                    freeSpaceFound = true;
                }
                freeSpaceCounter++;
                if (freeSpaceCounter == fileLength) {
                    return indexOfFreeSpace;
                }
            } else {
                freeSpaceCounter = 0;
                indexOfFreeSpace = -1;
                freeSpaceFound = false;
            }
        }
        return indexOfFreeSpace;
    }

    public int getLengthOfFileWithId(int id,
                                     List<Integer> fileSystem) {
        return (int) fileSystem.stream()
                .filter(f -> f == id)
                .count();
    }


    public void printList(List<Integer> list) {
        for (var n : list) {
            System.out.print((n == -1) ? "." : n);
        }
        System.out.println();
    }

    public List<Integer> sortFileSystem() {
        List<Integer> fileSystem = new ArrayList<>(diskMap.length());
        for (int i = 0; i < this.diskMap.length(); i++) {
            if (i % 2 == 0) {
                int files = valueAt(i);
                for (int j = 0; j < files; j++) {
                    fileSystem.add(i / 2);
                }
            } else {
                int freeSpace = valueAt(i);
                for (int j = 0; j < freeSpace; j++) {
                    fileSystem.add(-1);
                }
            }
        }
        return fileSystem;
    }


    public List<Integer> compactFileSystem(List<Integer> sortedFileSystem) {

        int leftPointer = 0;
        int rightPointer = sortedFileSystem.size() - 1;
        while (leftPointer < rightPointer) {
            if (sortedFileSystem.get(leftPointer) == -1) {
                Collections.swap(sortedFileSystem, leftPointer, rightPointer);
                do {
                    rightPointer--;
                } while (sortedFileSystem.get(rightPointer) == -1);
            }
            leftPointer++;
        }
        return sortedFileSystem;
    }

    private long checkSum2(List<Integer> compacted) {
        long checkSum = 0;
        int pos = 0;
        while (pos <= compacted.size() - 1 && compacted.get(pos) != -1) {
            checkSum += (long) pos * compacted.get(pos++);
        }
        return checkSum;
    }

    private long checkSum(List<Integer> compacted) {
        long checkSum = 0;
        int pos = 0;
        while (pos <= compacted.size() - 1) {
            int value = (compacted.get(pos) == -1) ? 0 : compacted.get(pos);
            checkSum += (long) pos++ * value;
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
