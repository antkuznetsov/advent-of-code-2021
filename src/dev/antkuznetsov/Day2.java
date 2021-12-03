package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws IOException {
        System.out.println(task1("data/day2.txt"));
        System.out.println(task2("data/day2.txt"));
    }

    public static int task1(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int horizontalPosition = 0;
            int depth = 0;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\s+");
                switch (data[0]) {
                    case "forward":
                        horizontalPosition += Integer.parseInt(data[1]);
                        break;
                    case "down":
                        depth += Integer.parseInt(data[1]);
                        break;
                    case "up":
                        depth -= Integer.parseInt(data[1]);
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
            return horizontalPosition * depth;
        }
    }

    public static int task2(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int horizontalPosition = 0;
            int depth = 0;
            int aim = 0;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\s+");
                switch (data[0]) {
                    case "forward":
                        horizontalPosition += Integer.parseInt(data[1]);
                        depth += aim * Integer.parseInt(data[1]);
                        break;
                    case "down":
                        aim += Integer.parseInt(data[1]);
                        break;
                    case "up":
                        aim -= Integer.parseInt(data[1]);
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
            return horizontalPosition * depth;
        }
    }
}
