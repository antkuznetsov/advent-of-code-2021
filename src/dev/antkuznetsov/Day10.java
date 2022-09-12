package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws IOException {
        System.out.println(task1("data/day10.txt"));
        System.out.println(task2("data/day10.txt"));
    }

    public static int task1(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            int res = 0;
            while (scanner.hasNextLine()) {
                res += validate(scanner.nextLine());
            }
            return res;
        }
    }

    public static long task2(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            List<Long> res = new ArrayList<>();
            while (scanner.hasNextLine()) {
                res.add(calculate(scanner.nextLine()));
            }
            res = res.stream().filter(el -> el != 0).sorted().collect(Collectors.toList());
            return res.get(res.size() / 2);
        }
    }

    public static int validate(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isOpenBracket(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return calculatePoints(c);
                }
                char open = stack.pop();
                if (open == '(' && c == ')') {
                    continue;
                } else if (open == '[' && c == ']') {
                    continue;
                } else if (open == '{' && c == '}') {
                    continue;
                } else if (open == '<' && c == '>') {
                    continue;
                }
                return calculatePoints(c);
            }
        }
        return calculatePoints(stack.pop());
    }

    public static long calculate(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isOpenBracket(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return 0;
                }
                char open = stack.pop();
                if (open == '(' && c == ')') {
                    continue;
                } else if (open == '[' && c == ']') {
                    continue;
                } else if (open == '{' && c == '}') {
                    continue;
                } else if (open == '<' && c == '>') {
                    continue;
                }
                return 0;
            }
        }
        long points = 0;
        while (!stack.isEmpty()) {
            points = points * 5 + calculateNewPoints(stack.pop());
        }
        return points;
    }

    private static boolean isOpenBracket(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }

    private static int calculatePoints(char c) {
        switch (c) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    private static int calculateNewPoints(char c) {
        switch (c) {
            case '(':
                return 1;
            case '[':
                return 2;
            case '{':
                return 3;
            case '<':
                return 4;
            default:
                return 0;
        }
    }
}
