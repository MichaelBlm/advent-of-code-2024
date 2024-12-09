package org.puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DayOne {

    public Integer partOne() {
        String path = "src/main/resources/dayOneInput.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            List<Integer> leftList = lines.stream()
                    .map(line -> Integer.parseInt(line.split("\\s+")[0]))
                    .sorted()
                    .toList();

            List<Integer> rightList = lines.stream()
                    .map(line -> Integer.parseInt(line.split("\\s+")[1]))
                    .sorted()
                    .toList();

            return IntStream.range(0, leftList.size())
                    .map(i -> Math.abs(leftList.get(i) - rightList.get(i)))
                    .sum();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer partTwo() {
        String path = "src/main/resources/dayOneInput.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            List<Integer> leftList = lines.stream()
                    .map(line -> Integer.parseInt(line.split("\\s+")[0]))
                    .toList();

            List<Integer> rightList = lines.stream()
                    .map(line -> Integer.parseInt(line.split("\\s+")[1]))
                    .toList();

            return leftList.stream()
                    .mapToInt(num -> num * Collections.frequency(rightList, num))
                    .sum();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        System.out.println("Part 1: " + dayOne.partOne());
        System.out.println("Part 2: " + dayOne.partTwo());

    }
}
