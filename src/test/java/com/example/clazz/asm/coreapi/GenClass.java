package com.example.clazz.asm.coreapi;

import com.example.utils.FileUtil;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.V1_5;

/**
 * @author James
 */
public class GenClass {

    @Test
    public void testGenClass() throws IOException {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                new String[] { "pkg/Mesurable" });
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        String filePath = "class/gen" + File.separator + "Comparable.class";
        FileUtil.writeBytesToFile(bytes, filePath, FileUtil.CLASS_BUFF_SIZE);
    }

    @Test
    public void testGenHello() throws IOException {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC ,
                "pkg/HelloWorld", null, "java/lang/Object",
                null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        String filePath = "class/gen" + File.separator + "HelloWorld.class";
        FileUtil.writeBytesToFile(bytes, filePath, FileUtil.CLASS_BUFF_SIZE);
    }
}
