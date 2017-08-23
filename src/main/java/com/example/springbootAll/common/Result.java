package com.example.springbootAll.common;

import lombok.Data;

/**
 * @Author: sea
 * @Description: 响应结果
 * @Date: 9:51 2017/8/23
 */
@Data
public class Result {

    //返回响应码
    private int code;

    //返回信息
    private String message;

    private Object data;

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(0,null,data);
    }

    public static Result failure(int code) {
        return failure(code,null);
    }

    public static Result failure(int code,String message) {
        return failure(code,message,null);
    }

    public static Result failure(int code,String message,Object data) {
        return new Result(code,message,data);
    }

    private Result(int code,String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
