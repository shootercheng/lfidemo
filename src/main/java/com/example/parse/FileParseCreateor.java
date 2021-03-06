package com.example.parse;

import com.example.constant.ParseType;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class FileParseCreateor {

    private static final class FileParseHolder {
        private static final FileParse csvFileParse = new CsvFileParse();
        private static final FileParse excelFileParse = new ExcelFileParse();
        private static final FileParse easyExcelParse = new EasyExcelParse();
    }

    public static FileParse createFileParse(ParseType parseType) {
        switch (parseType) {
            case CSV:
                return FileParseHolder.csvFileParse;
            case EXCEL:
                return FileParseHolder.excelFileParse;
            case EASYEXCEL:
                return FileParseHolder.easyExcelParse;
            default:
                throw new IllegalArgumentException("input file type error");
        }
    }
}
