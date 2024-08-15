package com.csg.rules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UppercaseWordCountRuleTest {

    private static UppercaseWordCountRule rule;

    @BeforeAll
    public static void setUp() {
        rule = new UppercaseWordCountRule();
    }

    @AfterEach
    public void tearDown() {
        rule.cleanUp();
    }

    @Test
    public void testNoUppercaseWords() {
        rule.apply("java software dev engineer test.");
        assertThat(rule.getCount(), equalTo(0));
    }

    @Test
    public void testOneUppercaseWord() {
        rule.apply("Java software dev engineer test.");
        assertThat(rule.getCount(), equalTo(1));
    }

    @Test
    public void testMultipleUppercaseWords() {
        rule.apply("Java Software Dev Engineer Test.");
        assertThat(rule.getCount(), equalTo(5));
    }
}
