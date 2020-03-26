package com.example.comment;

import com.example.exception.FileParseException;

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

    /**
     * 注解有括号跳过
     */
    private static List<String> exculdeAnnoList = Arrays.asList(
            "@Repository"
    );

    private static final String METHOD_PARAM = "\\(.*\\)";

    private static Pattern methodPattern = Pattern.compile(METHOD_PARAM, Pattern.DOTALL);

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
            throw new FileParseException("read file lines error ", e);
        }
        return resultList;
    }

    public static boolean isSkip(String line) {
        for (String anno : exculdeAnnoList) {
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
        if (params.trim().length() == 0) {
            return new ArrayList<>();
        }
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
        String filePath = "file/mapper/TestOne.java";
        String commentPath = "file/mapper/TestOne_Comment.java";
//        String commentPath = "file/mapper/TestOne.java";
        List<String> readLines = readAllLines(filePath, StandardCharsets.UTF_8);
        StringBuilder commentResult = new StringBuilder();
        for (int i = 0; i < readLines.size(); i++) {
            String codeLine = readLines.get(i);
            // 匹配方法
            if (codeLine.contains("(") && !isSkip(codeLine)) {
                if (!codeLine.endsWith(";")) {
                    // find method end ;
                    for (int j = i + 1; j < readLines.size(); j++) {
                        codeLine = codeLine + CRLF + readLines.get(j);
                        i++;
                        if (codeLine.endsWith(";")) {
                            break;
                        }
                    }
                    // 如果没有找到方法结束，直接报错
                    if (!codeLine.endsWith(";")) {
                        throw new IllegalArgumentException("java file format error");
                    }
                }
                Matcher matcher = methodPattern.matcher(codeLine);
                if (matcher.find()) {
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
                    stringBuilder.append(INDENT).append(" */").append(CRLF);
                    commentResult.append(stringBuilder);
                }
            }
            commentResult.append(codeLine).append(CRLF);
        }
        saveCodeComment(commentResult.toString(), commentPath);
    }
}
