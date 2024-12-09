package org.puzzles;

import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class DayTwo {

    private boolean isStrictlyIncreasing(int[] nums) {
        return IntStream.range(0, nums.length - 1)
                .allMatch(i -> nums[i] <= nums[i + 1]
                        && (Math.abs(nums[i] - nums[i + 1]) <= 3)
                        && (Math.abs(nums[i] - nums[i + 1]) > 0));
    }

    private boolean isStrictlyDecreasing(int[] nums) {
        return IntStream.range(0, nums.length - 1)
                .allMatch(i -> nums[i] >= nums[i + 1]
                        && (Math.abs(nums[i] - nums[i + 1]) <= 3)
                        && (Math.abs(nums[i] - nums[i + 1]) > 0));
    }

    private boolean canBeMonotonic(int[] nums) {
        return isStrictlyDecreasing(nums) || isStrictlyIncreasing(nums);
    }

    public long partOne() {
        String path = "src/main/resources/dayTwoInput.txt";
        try {
            return Files.lines(Paths.get(path))
                    .filter(line -> {
                        int[] nums = Arrays.stream(line.split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        return canBeMonotonic(nums);
                    }).count();


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read file");
        }
    }

    public long partTwo() {
        String path = "src/main/resources/dayTwoInput.txt";
        try {
            return Files.lines(Paths.get(path))
                    .filter(line -> {
                        int[] nums = Arrays.stream(line.split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        if (canBeMonotonic(nums)) {
                            return true;
                        }
                        return IntStream.range(0, nums.length)
                                .anyMatch(skip -> {
                                    int[] remaining = IntStream.range(0, nums.length)
                                            .filter(i -> i != skip)
                                            .map(i -> nums[i])
                                            .toArray();
                                    return canBeMonotonic(remaining);
                                });
                    }).count();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read file");

        }
    }

    public static void main(String[] args) {
        DayTwo dayTwo = new DayTwo();
        System.out.println("Part 1: " + dayTwo.partOne());
        System.out.println("Part 2: " + dayTwo.partTwo());
    }
}
