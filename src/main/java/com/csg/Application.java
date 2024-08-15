package com.csg;

import com.csg.rules.LongWordListRule;
import com.csg.rules.UppercaseWordCountRule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide at least one file as an argument.");
            return;
        }

        List<File> files = new ArrayList<>();
        for (String filePath : args) {
            files.add(new File(filePath));
        }

        var fileProcessor = new FileProcessor();
        fileProcessor.addIndexingRule(new UppercaseWordCountRule());
        fileProcessor.addIndexingRule(new LongWordListRule());

        for (File file : files) {
            fileProcessor.processFile(file);
        }

        fileProcessor.printResult();
    }
}