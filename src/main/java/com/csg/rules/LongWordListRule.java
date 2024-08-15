package com.csg.rules;

import java.util.ArrayList;
import java.util.List;

public class LongWordListRule implements IndexingRule {

    private List<String> longWords = new ArrayList<>();

    @Override
    public void apply(String line) {
        String[] words = line.split("\\s+");
        for (String word : words) {
            word = word.replaceAll("\\W", "");
            if (word.length() > 5) {
                longWords.add(word);
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Words longer than 5 characters:");
        for (String word : longWords) {
            System.out.println(word);
        }
    }

    @Override
    public void cleanUp() {
        this.longWords = new ArrayList<>();
    }

    public List<String> getLongWords() {
        return longWords;
    }
}
