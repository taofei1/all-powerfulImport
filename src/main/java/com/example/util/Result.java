package com.example.util;

public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.code = "01";
        result.message = "操作成功！";
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.code = "02";
        result.message = "操作失败！";
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result<T> addData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
