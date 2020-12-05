package com.tterrag.advent2020.taketwo;

import java.util.BitSet;

import com.tterrag.advent2020.util.Day;

public class Day05 extends Day {

    @Override
    protected Result doParts() {
        BitSet seats = lines().mapToInt(s -> Integer.parseInt(s.replace('F', '0').replace('B', '1').replace('L', '0').replace('R', '1'), 2))
                .collect(BitSet::new, (s, i) -> s.set(i), (i1, i2) -> i1.or(i2));
        return new Result(seats.previousSetBit(0xFFF), seats.stream().filter(i -> seats.get(i) && !seats.get(i + 1) && seats.get(i + 2)).findAny().orElse(-2) + 1);
    }
}
