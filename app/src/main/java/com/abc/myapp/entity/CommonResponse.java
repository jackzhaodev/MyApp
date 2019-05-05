package com.abc.myapp.entity;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class CommonResponse<T> {

    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
