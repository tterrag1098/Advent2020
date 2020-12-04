package com.tterrag.advent2020.days;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.tterrag.advent2020.util.Day;

import lombok.RequiredArgsConstructor;

public class Day04 extends Day {

    @RequiredArgsConstructor
    enum PassportField {

        byr(Integer::parseInt, i -> i >= 1920 && i <= 2002),
        iyr(Integer::parseInt, i -> i >= 2010 && i <= 2020),
        eyr(Integer::parseInt, i -> i >= 2020 && i <= 2030),
        hgt(s -> {
            int val = Integer.parseInt(s.substring(0, s.length() - 2));
            if (s.endsWith("cm")) {
                return val >= 150 && val <= 193;
            } else if (s.endsWith("in")) {
                return val >= 59 && val <= 76;
            }
            return false;
        }),
        hcl(s -> s.matches("#[a-f0-9]{6}")),
        ecl(s -> "amb".equals(s) || "blu".equals(s) || "brn".equals(s) || "gry".equals(s) || "grn".equals(s) || "hzl".equals(s) || "oth".equals(s)),
        pid(s -> s.matches("[0-9]{9}")),
        cid($ -> true),
        ;

        private <T> PassportField(Function<String, T> parser, Predicate<T> validator) {
            this(parser.andThen(validator::test)::apply);
        }

        private final Predicate<String> validator;

        boolean validate(String value) {
            try {
                return validator.test(value);
            } catch (Exception e) {
                return false;
            }
        }
    }

    class Passport {

        private final Map<PassportField, String> fields = new EnumMap<>(PassportField.class);

        void add(PassportField field, String value) {
            this.fields.put(field, value);
        }

        boolean isValid(boolean strict) {
            for (PassportField f : PassportField.values()) {
                if (f == PassportField.cid) continue;
                if (!fields.containsKey(f)) return false;
                if (strict && !f.validate(fields.get(f))) return false;
            }
            return true;
        }
    }

    final List<Passport> passports = new ArrayList<>();

    public Day04() {
        Passport passport = new Passport();
        for (String line : linesList()) {
            if (line.trim().length() == 0) {
                passports.add(passport);
                passport = new Passport();
            } else {
                String[] values = line.split(" ");
                for (String val : values) {
                    String[] keyval = val.split(":");
                    passport.add(PassportField.valueOf(keyval[0]), keyval[1]);
                }
            }
        }
        passports.add(passport);
    }

    @Override
    protected Object part1() {
        return passports.stream().filter(p -> p.isValid(false)).count();
    }

    @Override
    protected Object part2() {
        return passports.stream().filter(p -> p.isValid(true)).count();
    }
}
