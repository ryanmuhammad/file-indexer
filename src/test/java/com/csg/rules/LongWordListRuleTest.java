package com.csg.rules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LongWordListRuleTest {

    private static LongWordListRule rule;

    @BeforeAll
    public static void setUp() {
        rule = new LongWordListRule();
    }

    @AfterEach
    public void tearDown() {
        rule.cleanUp();
    }

    @Test
    public void testNoLongWords() {
        rule.apply("How are you?.");
        assertThat(rule.getLongWords(), is(empty()));
    }

    @Test
    public void testSomeLongWords() {
        rule.apply("You are to create an indexing process for a search system your team is building..");
        var longWords = rule.getLongWords();
        assertThat(longWords, hasItem("indexing"));
        assertThat(longWords, hasItem("process"));
        assertThat(longWords, hasItem("search"));
        assertThat(longWords, hasItem("building"));
        assertThat(longWords, hasItem("building"));
        assertThat(longWords, hasItem("create"));
    }
}
