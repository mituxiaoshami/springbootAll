package com.example.springbootAll.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @Author: sea
 * @Description: 全局异常处理
 * @Date: 9:13 2017/8/23
 */
// @ControllerAdvice 是一个@Component 用于定义@ ExceptionHandler的，@InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法
// 并处理所有@ RequestMapping标注方法出现异常的统一处理。
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    /**
     * 400 - Bad Request 缺少请求参数
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleHttpMessageNotReadableException(MissingServletRequestParameterException  exception) {
        logger.error("缺少请求参数",exception);
        return Result.failure(ExceptionCode.MISSING_SERVLET_REQUEST_PARAM,"缺少请求参数",exception);
    }

    /**
     * 400 - Bad Request 参数解析失败
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        logger.error("参数解析失败",exception);
        return Result.failure(ExceptionCode.HTTP_MESSAGE_NOT_READABLE,"参数解析失败",exception);
    }

    /**
     * 400 - Bad Request 参数验证失败
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.error("参数验证失败",exception);
        return Result.failure(ExceptionCode.METHOD_ARGUMENT_NOT_VALID,"参数验证失败",exception);
    }

    /**
     * 400 - Bad Request 参数绑定失败
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException exception) {
        logger.error("参数绑定失败",exception);
        return Result.failure(ExceptionCode.BIND_FAILURE,"参数绑定失败",exception);
    }

    /**
     * 400 - Bad Request 参数验证失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleServiceException(ConstraintViolationException exception) {
        logger.error("参数验证失败", exception);
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return Result.failure(ExceptionCode.CONSTRAINT_VIOLATION,"参数验证失败",message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException exception) {
        logger.error("参数验证失败", exception);
        return Result.failure(ExceptionCode.VALIDATION_FAILURE,"参数验证失败",exception);
    }

    /**
     * 405 - Method Not Allowed 不支持当前请求方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        logger.error("不支持当前请求方法", exception);
        return Result.failure(ExceptionCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED,"不支持当前请求方法",exception);
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        logger.error("不支持当前媒体类型", exception);
        return Result.failure(ExceptionCode.HTTP_MEDIATYPE_NOT_SUPPORTED,"不支持当前媒体类型",exception);
    }

    /**
     * 500 - Internal Server Error 业务逻辑异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException exception) {
        logger.error("业务逻辑异常", exception);
        return Result.failure(ExceptionCode.SERVICE_FAILURE,"业务逻辑异常",exception);
    }

    /**
     * 500 - Internal Server Error 通用异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception) {
        logger.error("通用异常", exception);
        return Result.failure(ExceptionCode.EXCEPTION_NOMAL,"通用异常",exception);
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleException(DataIntegrityViolationException exception) {
        logger.error("操作数据库出现异常:", exception);
        return Result.failure(ExceptionCode.DATA_INTEGRITY_VIOLATION,"数据库操作异常",exception);
    }
}
