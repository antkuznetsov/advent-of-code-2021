package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    private static List<Integer> data;

    public static void main(String[] args) throws IOException {
        initializeData("data/day7.txt");
        System.out.println(data);
        System.out.println("Task 1: " + task1());
        //initializeData("data/day6.txt");
        System.out.println("Task 2: " + task2());
    }

    public static void initializeData(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            data = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
    }

    public static long task1() {
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < data.size(); i++) {
            int baseEl = data.get(i);
            int sum = 0;
            for (int j = 0; j < data.size(); j++) {
                if (j != i) {
                    sum += Math.abs(baseEl - data.get(j));
                }
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        return minSum;
    }

    public static int task2() {
        int minSum = Integer.MAX_VALUE;
        Optional<Integer> min = data.stream().min(Integer::compareTo);
        Optional<Integer> max = data.stream().max(Integer::compareTo);
        for (int i = min.get(); i < max.get(); i++) {
            int baseEl = i;
            int sum = 0;
            for (int j = 0; j < data.size(); j++) {
                int fuel = 0;
                //if (j != i) {
                    int diff = Math.abs(baseEl - data.get(j));
                    for (int k = 1; k <= diff; k++) {
                        fuel += k;
                    }
                //}
                sum += fuel;
            }
            if (sum < minSum) {
                minSum = sum;
            }
        }
        return minSum;
    }
}
