package com.tterrag.advent2020.days;

import java.util.BitSet;

import com.tterrag.advent2020.util.Day;

public class Day06 extends Day {
    
    @Override
    protected Result doParts() {
        BitSet any = new BitSet();
        BitSet all = null;
        int part1 = 0, part2 = 0;
        for (String s : linesList()) {
            if (s.trim().isEmpty()) {
                part1 += any.cardinality();
                part2 += all.cardinality();
                any.clear();
                all = null;
                continue;
            }
            BitSet answers = new BitSet();
            s.trim().chars().forEach(answers::set);
            any.or(answers);
            if (all == null) {
                all = new BitSet();
                all.or(answers);
            } else {
                all.and(answers);
            }
        }
        part1 += any.cardinality();
        part2 += all.cardinality();
        return new Result(part1, part2);
    }
}
