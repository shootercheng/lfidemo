package com.example.comment;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
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
        DocComment docComment = new DocComment();
        docComment.visit(cu, null);
        InterfaceComment.saveCodeComment(cu.toString(), commentPath);
    }

    public static class DocComment extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            if (!md.getJavadoc().isPresent()) {
                addMethodComment(md);
            }
        }

        @Override
        public void visit(FieldDeclaration field, Void arg) {
            super.visit(field, arg);
            if (!field.getJavadoc().isPresent()) {
                addFieldComment(field);
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
//        stringBuilder.append(INDENT).append(" */").append(CRLF);
        md.setJavadocComment(stringBuilder.toString());
    }

    private static void addFieldComment(FieldDeclaration field) {
        List<VariableDeclarator> variableDeclarators = field.getVariables();
        if (variableDeclarators.size() > 0) {
            String variableName = variableDeclarators.get(0).getNameAsString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(INDENT).append(" * ").append(variableName).append(CRLF);
//            stringBuilder.append(INDENT).append(" */").append(CRLF);
            field.setJavadocComment(stringBuilder.toString());
        }
    }
}
