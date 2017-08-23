package com.example.springbootAll.common;

/**
 * @Author: sea
 * @Description: 自定义业务逻辑异常处理类
 * @Date: 11:13 2017/8/23
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String msg) {
        super(msg);
    }
}
