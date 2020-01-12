package com.example.exception;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class DataParseException extends RuntimeException {
    public DataParseException() {
        super();
    }

    public DataParseException(String msg) {
        super(msg);
    }
}
