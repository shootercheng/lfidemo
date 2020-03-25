package com.example.comment;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James
 */
public class CommentTest {
    private static Pattern diamondPattern = Pattern.compile("<.*?>");

    @Test
    public void testDiamond() {
        String testStr = "Map<String, List<Object>> map, Map<String, List<Map<String, Integer>>> imap";
        Matcher matcher = diamondPattern.matcher(testStr);
        String testStrRes = matcher.replaceAll("");
        testStrRes = testStrRes.replaceAll(">", "");
        System.out.println(testStrRes);
    }
}
