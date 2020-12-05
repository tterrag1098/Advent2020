package com.tterrag.advent2020.taketwo;

import java.util.BitSet;

import com.tterrag.advent2020.util.Day;

public class Day05 extends Day {

    @Override
    protected Result doParts() {
        BitSet seats = lines().mapToInt(s -> {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i);
                int val = (c >> 2) & 0b1;
                if (((c >> 3) & 0b11) == 0) val ^= 1;
                res = (res << 1) | val;
            }
            return res;
        }).collect(BitSet::new, (s, i) -> s.set(i), (i1, i2) -> i1.or(i2));
        return new Result(seats.previousSetBit(0xFFF), seats.stream().filter(i -> seats.get(i) && !seats.get(i + 1) && seats.get(i + 2)).findAny().orElse(-1));
    }
}
