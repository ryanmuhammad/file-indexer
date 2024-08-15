package com.csg;

import com.csg.rules.IndexingRule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private final List<IndexingRule> indexingRules = new ArrayList<>();

    public void addIndexingRule(IndexingRule rule) {
        indexingRules.add(rule);
    }

    public void processFile(File file) {
        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found: " + file.getPath());
            return;
        }

        System.out.println("Processing file: " + file.getPath());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (IndexingRule rule : indexingRules) {
                    rule.apply(line);
                }
            }

            for (var rule : indexingRules) {
                rule.printResult();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + file.getPath());
            e.printStackTrace();
        }
    }
}
