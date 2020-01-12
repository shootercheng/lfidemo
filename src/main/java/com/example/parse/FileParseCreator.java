package com.example.parse;

import com.example.constant.FileType;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class FileParseCreator {

    private static final class FileParseHolder {
        private static final FileParse csvFileParse = new CsvFileParse();
        private static final FileParse excelFileParse = new ExcelFileParse();
    }

    public static FileParse createFileParse(FileType fileType) {
        switch (fileType) {
            case CSV:
                return FileParseHolder.csvFileParse;
            case EXCEL:
                return FileParseHolder.excelFileParse;
            default:
                throw new IllegalArgumentException("input file type error");
        }
    }
}
