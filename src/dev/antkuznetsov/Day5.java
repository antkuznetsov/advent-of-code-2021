package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    private static final int FIELD_SIZE = 1000;
    private static int[][] field;

    public static void main(String[] args) throws IOException {
        initializeData("data/day5.txt", false);
        //printField();
        System.out.println("Task 1: " + result());
        initializeData("data/day5.txt", true);
        //printField();
        System.out.println("Task 2: " + result());
    }

    public static void initializeData(String fileName, boolean considerHorizontal) throws IOException {
        field = new int[FIELD_SIZE][FIELD_SIZE];
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] interval = line.split(" -> ");
                int[] start = Arrays.stream(interval[0].split(",")).mapToInt(Integer::parseInt).toArray();
                int[] finish = Arrays.stream(interval[1].split(",")).mapToInt(Integer::parseInt).toArray();
                if (start[0] == finish[0]) {
                    int s, f;
                    if (start[1] < finish[1]) {
                        s = start[1];
                        f = finish[1];
                    } else {
                        s = finish[1];
                        f = start[1];
                    }
                    for (int i = s; i <= f; i++) {
                        field[i][start[0]] += 1;
                    }
                } else if (start[1] == finish[1]) {
                    int s, f;
                    if (start[0] < finish[0]) {
                        s = start[0];
                        f = finish[0];
                    } else {
                        s = finish[0];
                        f = start[0];
                    }
                    for (int i = s; i <= f; i++) {
                        field[start[1]][i] += 1;
                    }
                } else if (start[0] <= finish[0] && start[1] <= finish[1] && considerHorizontal) {
                    for (int i = start[1], j = start[0]; i <= finish[1] && j <= finish[0]; i++, j++) {
                        field[i][j] += 1;
                    }
                } else if (start[0] >= finish[0] && start[1] >= finish[1] && considerHorizontal) {
                    for (int i = finish[1], j = finish[0]; i <= start[1] && j <= start[0]; i++, j++) {
                        field[i][j] += 1;
                    }
                } else if (start[1] < finish[1] && start[0] > finish[0] && considerHorizontal) {
                    for (int i = start[1], j = start[0]; i <= finish[1] && j >= finish[0]; i++, j--) {
                        field[i][j] += 1;
                    }
                } else if (start[1] > finish[1] && start[0] < finish[0] && considerHorizontal) {
                    for (int i = start[1], j = start[0]; i >= finish[1] && j <= finish[0]; i--, j++) {
                        field[i][j] += 1;
                    }
                }
            }
        }
    }

    public static int result() {
        int counter = 0;
        for (int[] row : field) {
            for (int el : row) {
                if (el >= 2) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static void printField() {
        for (int[] row : field) {
            for (int el : row) {
                System.out.print(el == 0 ? "." : el);
            }
            System.out.println();
        }
    }
}
