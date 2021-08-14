package com.pdsu.wl.crowd.util;

import com.pdsu.wl.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * 工具类
 * @author wl
 * @Date 2021/7/21 11:10
 */

public class CrowdUtil {

    /**
     * 对明文字符串进行MD5加密
     * @param source 传入的明文字符串
     * @return 加密结果
     */
    public static String MD5(String source) {

        // 1.判断source是否有效
        if (source == null || source.length() == 0) {

            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        // algorithm 表示算法名称
        String algorithm = "md5";


        try {
            // 3.获取MessageDigest对象
            final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);// 不允许使用魔法值,即 algorithm 直接用 md5,表意不清,其他人不知道什么意思

            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            final byte[] output = messageDigest.digest(input);

            // 6.创建BigInterger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照16进制将bigInteger的值转换为字符串
            int radix = 16;
            //final String encoded = bigInteger.toString(radix);    小写
            final String encoded = bigInteger.toString(radix).toUpperCase(Locale.ROOT); //大写

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断请求是普通请求还是Ajax请求(用于配置基于注解的异常映射)
     * @param request
     * @return true:ajax请求, false:普通请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1 获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2 判断
        if ((acceptHeader != null && acceptHeader.contains("application/json"))
                || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }
}
