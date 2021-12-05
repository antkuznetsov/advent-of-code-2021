package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4 {
    public static final int FIELD_SIZE = 5;
    private static List<Integer> marks;
    private static List<Field> fields = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initializeData("data/day4.txt");
        //System.out.println(marks);
        //System.out.println();
        for (Field field : fields) {
            //field.printField();
        }
        //System.out.println();

        for (Field field : fields) {
            for (int mark : marks) {
                if (!field.isWin()) {
                    field.mark(mark);
                }
            }
        }

        for (Field field : fields) {
            if (field.isWin()) {
                field.printField();
                System.out.println("Last mark: " + field.getLastMark());
                System.out.println("Step to win: " + field.getStepsToWin());
                System.out.println("Score: " + field.calculateScore());
                System.out.println();
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Task 1:");
        Field winner = fields.get(0);
        for (Field field : fields) {
            if (field.isWin()) {
                if (field.getStepsToWin() < winner.getStepsToWin()) {
                    winner = field;
                }
            }
        }
        winner.printField();
        System.out.println("Last mark: " + winner.getLastMark());
        System.out.println("Step to win: " + winner.getStepsToWin());
        System.out.println("Score: " + winner.calculateScore());
        System.out.println();

        System.out.println("--------------------------------------------------");
        System.out.println("Task 2:");
        winner = fields.get(0);
        for (Field field : fields) {
            if (field.isWin()) {
                if (field.getStepsToWin() > winner.getStepsToWin()) {
                    winner = field;
                }
            }
        }
        winner.printField();
        System.out.println("Last mark: " + winner.getLastMark());
        System.out.println("Step to win: " + winner.getStepsToWin());
        System.out.println("Score: " + winner.calculateScore());
        System.out.println();
    }

    public static void initializeData(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            marks = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            scanner.nextLine(); // skip first empty line
            int[][] data = new int[FIELD_SIZE][FIELD_SIZE];
            int rowCounter = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ("".equals(line)) {
                    fields.add(new Field(data));
                    data = new int[FIELD_SIZE][FIELD_SIZE];
                    rowCounter = 0;
                } else {
                    data[rowCounter] = Arrays.stream(line.strip().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                    rowCounter++;
                }
            }
            fields.add(new Field(data));
        }
    }

    static class Field {
        private int field[][];
        private int lastMark = -1;
        private int stepsToWin = -1;
        private boolean win = false;

        public Field(int[][] field) {
            this.field = field;
        }

        public void mark(int n) {
            for (int[] row : field) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == n && !win) {
                        row[i] = -1;
                        lastMark = n;
                        validate();
                    }
                    stepsToWin++;
                }
            }
        }

        private void validate () {
            // check horizontal lines
            OUTER: for (int i = 0; i < FIELD_SIZE; i++) {
                int count = 0;
                for (int j = 0; j < FIELD_SIZE; j++) {
                    if (field[i][j] < 0) {
                        count++;
                    }
                    if (count == FIELD_SIZE) {
                        win = true;
                        break OUTER;
                    }
                }
            }

            // check vertical lines
            OUTER: for (int i = 0; i < FIELD_SIZE; i++) {
                int counter = 0;
                for (int j = 0; j < FIELD_SIZE; j++) {
                    if (field[j][i] < 0) {
                        counter++;
                    }
                    if (counter == FIELD_SIZE) {
                        win = true;
                        break OUTER;
                    }
                }
            }
        }

        public int calculateScore() {
            int score = 0;
            for (int[] row : field) {
                for (int el : row) {
                    if (el >= 0) {
                        score += el;
                    }
                }
            }
            return score * lastMark;
        }

        public void printField() {
            for (int[] row : field) {
                for (int el : row) {
                    System.out.print(el + " ");
                }
                System.out.println();
            }
        }

        public int getLastMark() {
            return lastMark;
        }

        public boolean isWin() {
            return win;
        }

        public int getStepsToWin() {
            return stepsToWin;
        }
    }
}


