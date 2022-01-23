package com.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms50m -Xmx100m -XX:+HeapDumpOnOutOfMemoryError
 * @author James
 */
public class HeapOOM {

    public static void oomTest() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            byte[] byteData = new byte[1024 * 1024 * 100];
            list.add(byteData);
        }
    }

    public static void main(String[] args) {
        oomTest();
    }
}
