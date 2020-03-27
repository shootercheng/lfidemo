package com.example.comment;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.example.constant.CommonConstant.CRLF;
import static com.example.constant.CommonConstant.INDENT;

/**
 * @author James
 */
public class JavaParserComment {

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "file/mapper/TestOne.java";
        String commentPath = "file/mapper/TestOne_Parser.java";
        CompilationUnit cu = StaticJavaParser.parse(new File(filePath));
        MethodComment methodComment = new MethodComment();
        methodComment.visit(cu, null);
        InterfaceComment.saveCodeComment(cu.toString(), commentPath);
    }

    private static class MethodComment extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            if (!md.getJavadoc().isPresent()) {
                addMethodComment(md);
            }
        }
    }

    private static void addMethodComment(MethodDeclaration md) {
        StringBuilder stringBuilder = new StringBuilder();
        String returnType = md.getTypeAsString();
        String name = md.getNameAsString();
        stringBuilder.append(INDENT).append(" * ").append(name).append(CRLF);
        List<Parameter> paramList = md.getParameters();
        if (paramList.size() > 0) {
            for (Parameter param : paramList) {
                stringBuilder.append(INDENT).append(" * @param ")
                        .append(param.getNameAsString()).append(" ")
                        .append(param.getNameAsString()).append(CRLF);
            }
        }
        stringBuilder.append(INDENT).append(" * @return ")
                .append(returnType).append(CRLF);
        stringBuilder.append(INDENT).append(" */").append(CRLF);
        md.setJavadocComment(stringBuilder.toString());
    }
}
