package com.pdsu.wl.crowd.exception;

/**
 * 自定义用户没有登录时就访问受保护资源时抛出的异常
 * @author wl
 * @Date 2021/7/23 21:34
 */
public class AccessForbiddenException extends RuntimeException{
    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
