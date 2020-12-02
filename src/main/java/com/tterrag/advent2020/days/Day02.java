package com.tterrag.advent2020.days;

import com.tterrag.advent2020.util.Day;


public class Day02 extends Day {

    @Override
    protected Result doParts() {
        int valid1 = 0;
        int valid2 = 0;
        for (String s : linesList()) {
            String[] rules = s.split(" ");
            String[] minmax = rules[0].split("-");
            int min = Integer.parseInt(minmax[0]);
            int max = Integer.parseInt(minmax[1]);
            char c = rules[1].charAt(0);
            int occurences = rules[2].length() - rules[2].replace(String.valueOf(c), "").length();
            if (occurences >= min && occurences <= max) {
                valid1++;
            }
            char a = rules[2].charAt(min - 1);
            char b = rules[2].charAt(max - 1);
            if ((a == c || b == c) && a != b) {
                valid2++;
            }
        }
        return new Result(valid1, valid2);
    }
}
