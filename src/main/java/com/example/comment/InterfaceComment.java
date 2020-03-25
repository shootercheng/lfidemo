package com.example.comment;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James
 */
public class InterfaceComment {

    private static final String INDENT = "    ";

    private static final String CRLF = System.lineSeparator();

    private static List<String> exculdeAnnnList = Arrays.asList(
            "@Repository"
    );

    private static final String METHOD_PARAM = "\\(.*\\)";

    private static Pattern methodPattern = Pattern.compile(METHOD_PARAM);

    private static Pattern paramAnnoPattern = Pattern.compile("@.*?\\)");

    private static Pattern diamondPattern = Pattern.compile("<.*?>");

    public static List<String> readAllLines(String filePath, Charset charset) {
        List<String> resultList = new ArrayList<>(100);
        try (Reader inputStreamReader = new InputStreamReader(new FileInputStream(filePath), charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String curLine;
            while ((curLine = bufferedReader.readLine()) != null) {
                resultList.add(curLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static boolean isSkip(String line) {
        for (String anno : exculdeAnnnList) {
            if (line.contains(anno)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Map<String, List<Object>>
     * @param inputArr
     * @return
     */
    public static String findType(String[] inputArr) {
        if (inputArr.length == 2) {
            return inputArr[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < inputArr.length - 1; i++) {
            stringBuilder.append(inputArr[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static List<String> findParamList(String params) {
        // 替换方法上的注解 @Param("tableName")
        Matcher annoMatcher = paramAnnoPattern.matcher(params);
        if (annoMatcher.find()) {
            params = annoMatcher.replaceAll("");
        }
        // remove 左右 括号
        params = params.substring(1, params.length() - 1);
        // Map<String, List<Object>> map, Map<String, List<Map<String, Integer>>> imap
        if (params.indexOf("Map") != -1) {
            params = diamondPattern.matcher(params).replaceAll("");
            params = params.replaceAll(">", "");
        }
        String[] paramArrs = params.split(",");
        List<String> paramList = new ArrayList<>(paramArrs.length);
        for (String param : paramArrs) {
            String[] paramTypeNameArr = param.trim().split(" ");
            if (paramTypeNameArr.length == 2) {
                paramList.add(paramTypeNameArr[1]);
            }
        }
        return paramList;
    }

    public static void saveCodeComment(String comment, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath, false),
                        StandardCharsets.UTF_8))) {
            bufferedWriter.write(comment);
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        String filePath = "E:\\Github\\lfidemo\\file\\mapper\\BatchLabelMapper.java";
        List<String> readLines = readAllLines(filePath, StandardCharsets.UTF_8);
        int j = 0;
        StringBuilder commentResult = new StringBuilder();
        for (int i = 0; i < readLines.size() - 1; i++) {
            String codeLine = readLines.get(i + 1);
            // 匹配参数
            Matcher matcher = methodPattern.matcher(codeLine);
            if (matcher.find() && !isSkip(codeLine)) {
                if (j > 0) {
                    commentResult.append(CRLF);
                }
                StringBuilder stringBuilder = new StringBuilder(INDENT).append("/**").append(CRLF);
                String params = matcher.group();
                String methodName = matcher.replaceAll("").trim();
                if (methodName.endsWith(";")) {
                    methodName = methodName.substring(0, methodName.length() - 1);
                }
                String[] nameTypeArr = methodName.split(" ");
                String returnType = findType(nameTypeArr);
                String name = nameTypeArr[nameTypeArr.length - 1];
                stringBuilder.append(INDENT).append(" * ").append(name).append(CRLF);
                List<String> paramList = findParamList(params);
                for (String param : paramList) {
                    stringBuilder.append(INDENT).append(" * @param ")
                            .append(param).append(" ").append(param).append(CRLF);
                }
                stringBuilder.append(INDENT).append(" * @return ").append(returnType).append(CRLF);
                stringBuilder.append(INDENT).append(" */");
                commentResult.append(stringBuilder);
                j++;
            }
            commentResult.append(readLines.get(i)).append(CRLF);
        }
        commentResult.append(readLines.get(readLines.size() - 1));
        String commentPath = "E:\\Github\\lfidemo\\file\\mapper\\BatchLabelMapper_Comment.java";
        saveCodeComment(commentResult.toString(), commentPath);
    }
}
