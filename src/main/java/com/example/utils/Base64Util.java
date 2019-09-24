package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author James Chen
 * @date 24/09/19
 */
public class Base64Util {


    private static final class Base64Holder {
        static final BASE64Encoder base64Encoder = new BASE64Encoder();
        static final BASE64Decoder base64Decoder = new BASE64Decoder();
    }


    public static byte[] decryptBASE64(String key) throws IOException {
        return Base64Holder.base64Decoder.decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64Holder.base64Encoder.encodeBuffer(key);
    }

    public static String encryptImgBASE64(String picPath) {
        String content = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(picPath);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            content = Base64Holder.base64Encoder.encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }


    public static void decryptBASE64Img(String str, String picPath) {
        RandomAccessFile randomAccessFile = null;
        try {
            byte[] result = Base64Holder.base64Decoder.decodeBuffer(str.trim());
            randomAccessFile = new RandomAccessFile(picPath, "rw");
            randomAccessFile.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}