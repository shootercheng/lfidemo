package com.example.utils;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;

/**
 * @author James Chen
 * @date 24/09/19
 */
public class ImageUtil {

    private static class PNGTranscoderHolder {
        static final PNGTranscoder pngTranscoder = new PNGTranscoder();
    }

    public static void convertSvgToPng(String svgPath, String dirPath, String targetName) {
        String svgBase64 = Base64Util.encryptImgBASE64(svgPath);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(dirPath + File.separator + targetName);
            byte[] bytes = Base64Util.decryptBASE64(svgBase64);
            TranscoderInput transcoderInput = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput transcoderOutput = new TranscoderOutput(outputStream);
            PNGTranscoderHolder.pngTranscoder.transcode(transcoderInput, transcoderOutput);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
