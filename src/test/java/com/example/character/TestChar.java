package com.example.character;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author chengdu
 * @date 2020/1/23
 */
public class TestChar {

    @Test
    public void testIllegalChar() {
        char[] errorCharArr = {'\ufeff', 't', 'i', 'm', 'e'};
        char[] errorCharArrTwo = {'\uFEFF', 't', 'i', 'm', 'e'};
        String errorStr = new String(errorCharArr);
        String errorStrTwo = new String(errorCharArrTwo);
        System.out.println(errorStr);
        System.out.println(errorStrTwo);
        for (char c : errorCharArr) {
            if (c == '\ufeff') {
                System.out.println("------------------------error");
            }
        }
        for (char c : errorCharArrTwo) {
            if (c == '\ufeff') {
                System.out.println("------------------------error");
            }
        }
        char[] illegalChars = {'\ufeff'};
        char[] rightChars = "id".toCharArray();
        char[] errorChars = new char[1 + rightChars.length];
        for (int i = 0; i < errorChars.length; i++) {
            if (i == 0) {
                errorChars[0] = illegalChars[0];
            } else {
                errorChars[i] = rightChars[i - 1];
            }
        }
        System.out.println(new String(errorChars));
    }

    @Test
    public void testByteOutArray() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String str = bos.toString();
        Assert.assertEquals(0, str.length());
        System.out.println(str);
    }
}
