package com.example.clazz.asm.coreapi;

import com.example.utils.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
}
