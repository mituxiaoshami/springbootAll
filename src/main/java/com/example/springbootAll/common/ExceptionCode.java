package com.example.springbootAll.common;

/**
 * @Author: sea
 * @Description: 异常错误码
 * @Date: 10:01 2017/8/23
 */
public class ExceptionCode {

    /**
     * 缺少请求参数
     */
    public static final int MISSING_SERVLET_REQUEST_PARAM = 1111;

    /**
     * 参数解析失败
     */
    public static final int HTTP_MESSAGE_NOT_READABLE = 2222;

    /**
     * 参数验证失败
     */
    public static final int METHOD_ARGUMENT_NOT_VALID = 3333;

    /**
     * 参数绑定失败
     */
    public static final int BIND_FAILURE = 4444;

    /**
     * 参数验证失败
     */
    public static final int CONSTRAINT_VIOLATION = 5555;


    /**
     * 参数验证失败
     */
    public static final int VALIDATION_FAILURE = 6666;

    /**
     * 不支持当前请求方法
     */
    public static final int HTTP_REQUEST_METHOD_NOT_SUPPORTED = 7777;


    /**
     * 不支持当前媒体类型
     */
    public static final int HTTP_MEDIATYPE_NOT_SUPPORTED = 8888;

    /**
     * 业务逻辑异常
     */
    public static final int SERVICE_FAILURE = 9999;

    /**
     * 通用异常
     */
    public static final int EXCEPTION_NOMAL = 0000;


    /**
     * 数据库出现异常：字段重复、有外键关联等
     */
    public static final int DATA_INTEGRITY_VIOLATION = 11111;
}
