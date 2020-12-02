package com.tterrag.advent2020.days;

import com.tterrag.advent2020.util.Day;

public class Day01 extends Day {

    private final int[] input = lines().mapToInt(Integer::parseInt).toArray();

    @Override
    protected Object part1() {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == 2020) {
                    return input[i] * input[j];
                }
            }
        }
        return null;
    }
    
    @Override
    protected Object part2() {   
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (input[i] + input[j] + input[k] == 2020) {
                        return input[i] * input[j] * input[k];
                    }
                }
            }
        }
        return null;
    }
}
