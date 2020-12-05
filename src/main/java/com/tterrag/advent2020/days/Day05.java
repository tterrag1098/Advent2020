package com.tterrag.advent2020.days;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.tterrag.advent2020.util.Day;

public class Day05 extends Day {

    Set<Integer> seats = lines().map(s -> {
        int size = 128;
        int row = 0;
        for (int i = 0; i < 7; i++) {
            size /= 2;
            if (s.charAt(i) == 'B') {
                row += size;
            }
        }
        size = 8;
        int col = 0;
        for (int i = 7; i < 10; i++) {
            size /= 2;
            if (s.charAt(i) == 'R') {
                col += size;
            }
        }

        return (row * 8) + col;
    }).collect(Collectors.toSet());

    @Override
    protected Object part1() {
        return Collections.max(seats);
    }

    @Override
    protected Object part2() {
        for (int i = 0;; i++) {
            if (!seats.contains(i) && seats.contains(i - 1) && seats.contains(i + 1)) {
                return i;
            }
        }
    }
}
