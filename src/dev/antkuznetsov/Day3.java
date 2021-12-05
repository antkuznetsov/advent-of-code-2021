package dev.antkuznetsov;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    private static final List<String> data = new ArrayList<>();
    private static final Map<Integer, ArrayList<String>> frequencies = new HashMap<>();
    private static final int LINE_LENGTH = 12;
    private static final String ZERO = "0";
    private static final String ONE = "1";

    public static void main(String[] args) throws IOException {
        initializeData("data/day3.txt");

        System.out.println(task1());
        System.out.println(task2());
    }

    public static void initializeData(String fileName) throws IOException {
        for (int i = 0; i < LINE_LENGTH; i++) {
            frequencies.put(i, new ArrayList<>());
        }

        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
                String[] bits = line.split("");
                for (int i = 0; i < bits.length; i++) {
                    frequencies.get(i).add(bits[i]);
                }
            }
        }
    }

    public static long task1() {
        StringBuilder tempGammaRate = new StringBuilder();
        StringBuilder tempEpsilonRate = new StringBuilder();

        for (int i = 0; i < LINE_LENGTH; i++) {
            long countZeros = frequencies.get(i).stream().filter(ZERO::equals).count();
            long countOnes = frequencies.get(i).stream().filter(ONE::equals).count();
            if (countZeros > countOnes) {
                tempGammaRate.append(ZERO);
                tempEpsilonRate.append(ONE);
            } else {
                tempGammaRate.append(ONE);
                tempEpsilonRate.append(ZERO);
            }
        }

        long gammaRate = Long.parseLong(tempGammaRate.toString(), 2);
        long epsilonRate = Long.parseLong(tempEpsilonRate.toString(), 2);

        return gammaRate * epsilonRate;
    }

    public static long task2() {
        String tempOxygenGeneratorRating = filterOxygenGeneratorRating(data, 0);
        String tempCO2ScrubberRating = filterCO2ScrubberRating(data, 0);

        long oxygenGeneratorRating = Long.parseLong(tempOxygenGeneratorRating, 2);
        long CO2ScrubberRating = Long.parseLong(tempCO2ScrubberRating, 2);

        return oxygenGeneratorRating * CO2ScrubberRating;
    }

    private static String filterOxygenGeneratorRating(final List<String> data, final int position) {
        List<String> bitsForPosition = data.stream().map(el -> String.valueOf(el.charAt(position))).collect(Collectors.toList());
        long countZeros = bitsForPosition.stream().filter(ZERO::equals).count();
        long countOnes = bitsForPosition.stream().filter(ONE::equals).count();
        String filterBit = countZeros > countOnes ? ZERO : ONE;

        List<String> tempData = data.stream().filter(el -> String.valueOf(el.charAt(position)).equals(filterBit)).collect(Collectors.toList());

        if (tempData.size() > 1) {
            return filterOxygenGeneratorRating(tempData, position + 1);
        }
        return data.get(0);
    }

    private static String filterCO2ScrubberRating(List<String> data, int position) {
        List<String> bitsForPosition = data.stream().map(el -> String.valueOf(el.charAt(position))).collect(Collectors.toList());
        long countZeros = bitsForPosition.stream().filter(ZERO::equals).count();
        long countOnes = bitsForPosition.stream().filter(ONE::equals).count();
        String filterBit = countZeros <= countOnes ? ZERO : ONE;

        List<String> tempData = data.stream().filter(el -> String.valueOf(el.charAt(position)).equals(filterBit)).collect(Collectors.toList());

        if (tempData.size() > 1) {
            return filterCO2ScrubberRating(tempData, position + 1);
        }
        return data.get(0);
    }
}
