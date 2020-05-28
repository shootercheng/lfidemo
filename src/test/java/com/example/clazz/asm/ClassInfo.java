package com.example.clazz.asm;

import com.example.utils.FileUtil;
import org.junit.Test;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;

import java.io.IOException;

import static org.springframework.asm.Opcodes.ASM5;

public class ClassInfo {

    @Test
    public void showClassInfo() throws IOException {
        String filePath = "class/Hello.class";
        byte[] clazzByte = FileUtil.readFileByte(filePath);
        ClassReader classReader = new ClassReader(clazzByte);
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM5, classWriter) {
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println(name);
                return this.cv != null ? this.cv.visitField(access, name, descriptor, signature, value) : null;
            }

            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println(name);
                return this.cv != null ? this.cv.visitMethod(access, name, descriptor, signature, exceptions) : null;
            }
        };
        classReader.accept(classVisitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
    }
}
