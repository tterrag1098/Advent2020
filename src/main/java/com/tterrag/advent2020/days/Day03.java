package com.tterrag.advent2020.days;

import com.tterrag.advent2020.util.Day;

public class Day03 extends Day {

    Boolean[][] map = lines()
            .map(s -> s.chars().mapToObj(i -> (Boolean) (i == (int) '#')).toArray(Boolean[]::new))
            .toArray(Boolean[][]::new);

    @Override
    protected Object part1() {
        return treesForSlope(3, 1);
    }

    @Override
    protected Object part2() {
        return treesForSlope(1, 1) * treesForSlope(3, 1) * treesForSlope(5, 1) * treesForSlope(7, 1) * treesForSlope(1, 2);
    }

    private long treesForSlope(int right, int down) {
        int x = 0, y = 0;
        int trees = 0;
        while (y < map.length) {
            if (map[y][x]) {
                trees++;
            }
            x = (x + right) % map[y].length;
            y += down;
        }
        System.out.println(trees);
        return trees;
    }
}
