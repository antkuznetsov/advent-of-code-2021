package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day6 {
    private static List<Integer> data;

    public static void main(String[] args) throws IOException {
        initializeData("data/day6.txt");
        System.out.println("Task 1: " + task1(80));
        initializeData("data/day6.txt");
        System.out.println("Task 2: " + task2(256));
    }

    public static void initializeData(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            data = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
    }

    // I keep this code just to show how ineffective straightforward solution can be
    public static int task1(int period) {
        for (int i = 1; i <= period; i++) {
            List<Integer> newGeneration = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                int el = data.get(j);
                if (el == 0) {
                    data.set(j, 6);
                    newGeneration.add(8);
                } else {
                    data.set(j, el - 1);
                }
            }
            data.addAll(newGeneration);
            //System.out.printf("After day %d: %s%n", i, data);
        }
        return data.size();
    }

    public static long task2(int period) {
        long[] map = new long[9];
        // Prepare Initial state
        for (int i = 0; i < map.length; i++) {
            final int age = i;
            map[i] = data.stream().filter(el -> el == age).count();
        }
        //System.out.printf("Initial state: %s%n", Arrays.toString(map));

        for (int i = 1; i <= period; i++) {
            long ageZeroCount = map[0];
            System.arraycopy(map, 1, map, 0, map.length - 1); // shift to left
            map[6] += ageZeroCount;
            map[8] = ageZeroCount;
            //System.out.printf("After day %d: %s%n", i, Arrays.toString(map));
        }
        return Arrays.stream(map).sum();
    }
}
