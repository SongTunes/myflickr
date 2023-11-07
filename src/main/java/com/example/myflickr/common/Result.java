package com.example.myflickr.common;


public class Result {
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    private String code;
    private String msg;
    private Object data;

    // 可以不写默认构造函数?
    public Result() {
    }
    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static Result success() {
        return new Result(CODE_SUCCESS, "request ok", null);
    }

    public static Result success(String msg) {
        return new Result(CODE_SUCCESS, msg, null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "request ok", data);
    }

    public static Result success(String msg, Object data){
        return new Result(CODE_SUCCESS, msg, data);
    }
    public static Result error(String code, String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
