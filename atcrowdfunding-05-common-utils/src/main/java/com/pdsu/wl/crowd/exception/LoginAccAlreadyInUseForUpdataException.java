package com.pdsu.wl.crowd.exception;

/**
 * 更新Admin时如果检测到登录账号重复,抛出这个异常
 * @author wl
 * @Date 2021/7/29 19:31
 */
public class LoginAccAlreadyInUseForUpdataException extends RuntimeException {

    public LoginAccAlreadyInUseForUpdataException() {
        super();
    }

    public LoginAccAlreadyInUseForUpdataException(String message) {
        super(message);
    }

    public LoginAccAlreadyInUseForUpdataException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccAlreadyInUseForUpdataException(Throwable cause) {
        super(cause);
    }

    protected LoginAccAlreadyInUseForUpdataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
