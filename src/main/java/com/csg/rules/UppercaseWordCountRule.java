package com.csg.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UppercaseWordCountRule implements IndexingRule {

    private int count = 0;
    private final Pattern pattern = Pattern.compile("\\b[A-Z][a-zA-Z]*\\b");

    @Override
    public void apply(String line) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            count++;
        }
    }

    @Override
    public void printResult() {
        System.out.println("Number of words starting with uppercase letters: " + count);
    }

    public int getCount() {
        return this.count;
    }

    public int setCount(int count) {
        return this.count = count;
    }
}
