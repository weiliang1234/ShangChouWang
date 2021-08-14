package com.pdsu.wl.crowd.util;

import javax.xml.crypto.Data;

/**
 * 统一整个项目中Ajax请求返回的结果(未来也可以用于分布式架构各个模块间调用时返回统一类型)
 * @author wl
 * @Date 2021/7/20 10:45
 */

// 规定返回数据的格式
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String Failed = "FAILED";
    public static final Integer statusCode = 200;

    // 用来封装当前请求处理的结果是成功还失败
    private String result;

    // 请求处理失败时返回的错误消息
    private String message;

    // 要返回的数据
    private T data;

    private Integer status;

    /**
     * 请求成功且不需要返回数据时使用的工具方法
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<E>(SUCCESS, null, null);
    }

    /**
     * 请求成功需要返回数据时使用的工具方法
     * @param Data  要返回的数据
     * @param <E>   泛型
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E Data) {
        return new ResultEntity<E>(SUCCESS, null, Data, statusCode);
    }



    /**
     * 请求处理失败后使用的工具方法
     * @param Message  失败的错误消息
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> Failed(String Message) {
        return new ResultEntity<E>(Failed, Message, null);
    }

    public ResultEntity() {

    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity(String result, String message, T data, Integer status) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
