package com.liym.servicebase.exception;

// 自定义异常类
public class MyException extends RuntimeException{
    private Integer code; // 状态码

    private String msg; // 异常信息

    public MyException() {
    }

    public MyException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
