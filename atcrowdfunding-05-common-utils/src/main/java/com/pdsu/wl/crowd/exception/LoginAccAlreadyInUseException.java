package com.pdsu.wl.crowd.exception;

/**
 * 保存或更新Admin时如果检测到登录账号重复,抛出这个异常
 * @author wl
 * @Date 2021/7/29 19:31
 */
public class LoginAccAlreadyInUseException extends RuntimeException {

    public LoginAccAlreadyInUseException() {
        super();
    }

    public LoginAccAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAccAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    protected LoginAccAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
