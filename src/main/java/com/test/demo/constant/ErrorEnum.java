package com.test.demo.constant;


import lombok.Data;

public enum ErrorEnum {

    FRIST_ERROR_CODE("10001","第1个错误"),
    SECOND_ERROR_CODE("10001","第2个错误"),
    THIRD_ERROR_CODE("10001","第3个错误"),
    FORTH_ERROR_CODE("10001","第4个错误"),
    FIVE_ERROR_CODE("10001","第5个错误"),
    SIX_ERROR_CODE("10001","第6个错误");

    private String code;
    private String msg;

    ErrorEnum(String code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
