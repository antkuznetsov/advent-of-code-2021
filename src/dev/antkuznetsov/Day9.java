package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    private static final int ROW = 100;
    private static final int[][] field = new int[ROW][];

    public static void main(String[] args) throws IOException {
        initializeData("data/day9.txt");
        printField();
        System.out.println("--");
        System.out.println(task1());
    }

    public static int task1() {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int el = field[i][j];
                if (i == 0) {
                    if (j == 0) {
                        if (el < field[i + 1][j] && el < field[i][j + 1]) {
                            res.add(el);
                        }
                    } else if (j == field[i].length - 1) {
                        if (el < field[i + 1][j] && el < field[i][j - 1]) {
                            res.add(el);
                        }
                    } else {
                        if (el < field[i][j - 1] && el < field[i][j + 1] && el < field[i + 1][j]) {
                            res.add(el);
                        }
                    }
                } else if (i == ROW - 1) {
                    if (j == 0) {
                        if (el < field[i - 1][j] && el < field[i][j + 1]) {
                            res.add(el);
                        }
                    } else if (j == field[i].length - 1) {
                        if (el < field[i][j - 1] && el < field[i - 1][j]) {
                            res.add(el);
                        }
                    } else {
                        if (el < field[i][j - 1] && el < field[i][j + 1] && el < field[i - 1][j]) {
                            res.add(el);
                        }
                    }
                } else {
                    if (j == 0) {
                        if (el < field[i][j + 1] && el < field[i - 1][j] && el < field[i + 1][j]) {
                            res.add(field[i][j]);
                        }
                    } else if (j == field[i].length - 1) {
                        if (el < field[i][j - 1] && el < field[i - 1][j] && el < field[i + 1][j]) {
                            res.add(field[i][j]);
                        }
                    } else {
                        if (el < field[i][j - 1] && el < field[i][j + 1] && el < field[i - 1][j] && el < field[i + 1][j]) {
                            res.add(field[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println(res);
        return res.stream().mapToInt(el -> el + 1).sum();
    }

    public static void initializeData(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int row = 0;
            while (scanner.hasNextLine()) {
                field[row] = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
                row++;
            }
        }
    }

    private static void printField() {
        for (int[] row : field) {
            for (int el : row) {
                System.out.print(el);
            }
            System.out.println();
        }
    }
}
