package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args) throws IOException {
        System.out.println(task1("data/day8.txt"));
    }

    public static int task1(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            StringBuilder res = new StringBuilder();
            while (scanner.hasNextLine()) {
                String[] input = scanner.nextLine().split(" \\| ");
                String[] signalPatterns = input[0].split("\\s+");
                String[] outputNumbers = input[1].split("\\s+");

                assert signalPatterns.length == 10;
                assert outputNumbers.length == 4;

                System.out.println(Arrays.toString(signalPatterns) + " " + Arrays.toString(outputNumbers));

                for (String el : outputNumbers) {
                    switch (el.length()) {
                        case 2:
                            res.append(1);
                            break;
                        case 4:
                            res.append(4);
                            break;
                        case 3:
                            res.append(7);
                            break;
                        case 7:
                            res.append(8);
                            break;
                    }
                }
            }
            return res.length();
        }
    }

    private static class Number {
        private boolean[] segments;

        private boolean top;
        private boolean topLeft;
        private boolean topRight;
        private boolean middle;
        private boolean bottomLeft;
        private boolean bottomRight;
        private boolean bottom;

        public Number(boolean top, boolean topLeft, boolean topRight, boolean middle, boolean bottomLeft, boolean bottomRight, boolean bottom) {
            this.top = top;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.middle = middle;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
            this.bottom = bottom;
        }

        public Number(boolean[] segments) {
            this.segments = segments;
        }


    }
}
