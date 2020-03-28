package com.example.comment;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James
 */
public class CommentTest {
    private static Pattern diamondPattern = Pattern.compile("<.*?>");

    private static Pattern paramPattern = Pattern.compile("(.*)", Pattern.DOTALL);

    @Test
    public void testDiamond() {
        String testStr = "Map<String, List<Object>> map, Map<String, List<Map<String, Integer>>> imap";
        Matcher matcher = diamondPattern.matcher(testStr);
        String testStrRes = matcher.replaceAll("");
        testStrRes = testStrRes.replaceAll(">", "");
        System.out.println(testStrRes);
    }

    @Test
    public void testMatch() {
        String testStr = "List<String> selectList(@Param(value = \"map\") Map<String, Object> map,\n" +
                "                            @Param(value = \"listMap\") List<Map<String, Integer>> listMap);";
        Matcher matcher = paramPattern.matcher(testStr);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void testField() throws FileNotFoundException {
        String filePath = "src\\main\\java\\com\\example\\constant\\CommonConstant.java";
        CompilationUnit cu = StaticJavaParser.parse(new File(filePath));
        JavaParserComment.DocComment docComment = new JavaParserComment.DocComment();
        docComment.visit(cu, null);
        System.out.println(cu.toString());
    }
}
