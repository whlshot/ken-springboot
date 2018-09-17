package com.ken.exception;

import com.ken.util.ResultEnum;

public class ResultException extends RuntimeException {

    private int code;

    public ResultException() {

    }

    public ResultException(String message) {
        this(ResultEnum.ERROR.getCode(), message);
    }

    public ResultException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ResultException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
