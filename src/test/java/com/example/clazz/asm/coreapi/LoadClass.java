package com.example.clazz.asm.coreapi;

import com.example.utils.FileUtil;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.V1_5;

/**
 * @author James
 */
public class LoadClass {

    @Test
    public void loadClass() throws IOException {
        String filePath = "class/gen" + File.separator + "HelloWorld.class";
        byte[] bytes = FileUtil.readFileByte(filePath);
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.defineClass("pkg.HelloWorld", bytes);
        System.out.println(clazz);
    }

    @Test
    public void testLoadGenClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC ,
                "pkg/HelloWorld", null, "java/lang/Object",
                null);
        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.defineClass("pkg.HelloWorld", bytes);
        System.out.println(clazz);
    }
}
