package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException {
        System.out.println(task1("data/day1.txt"));
        System.out.println(task2("data/day1.txt"));
    }

    public static int task1(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int counter = 0;
            int prevEl = scanner.nextInt();

            while (scanner.hasNextInt()) {
                int currentEl = scanner.nextInt();
                if (currentEl > prevEl) {
                    counter++;
                }
                prevEl = currentEl;
            }
            return counter;
        }
    }

    public static int task2(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int counter = 0;

            int prevEl1 = scanner.nextInt();
            int prevEl2 = scanner.nextInt();
            int prevEl3 = scanner.nextInt();

            while (scanner.hasNextInt()) {
                int currentEl1 = prevEl2;
                int currentEl2 = prevEl3;
                int currentEl3 = scanner.nextInt();

                int prevSum = prevEl1 + prevEl2 + prevEl3;
                int currentSum = currentEl1 + currentEl2 + currentEl3;

                if (currentSum > prevSum) {
                    counter++;
                }

                prevEl1 = currentEl1;
                prevEl2 = currentEl2;
                prevEl3 = currentEl3;
            }
            return counter;
        }
    }
}
