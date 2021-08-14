package com.pdsu.wl.crowd.mvc.config;

import com.google.gson.Gson;
import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.exception.LoginAccAlreadyInUseException;
import com.pdsu.wl.crowd.exception.LoginAccAlreadyInUseForUpdataException;
import com.pdsu.wl.crowd.exception.LoginFailedException;
import com.pdsu.wl.crowd.util.CrowdUtil;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wl
 * @Date 2021/7/21 11:47
 */
@ControllerAdvice  // @ControllerAdvice 表示当前类是一个基于注解的异常处理器类
public class CrowdExceptionResolver {

    /**
     * 新增时捕获账户重复插入异常
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = LoginAccAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(LoginAccAlreadyInUseException exception,
                                  HttpServletRequest request, HttpServletResponse response) {

        String viewName = "admin-add";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 更新时捕获账号重复异常
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = LoginAccAlreadyInUseForUpdataException.class)
    public ModelAndView LoginAccAlreadyInUseForUpdataException(LoginAccAlreadyInUseForUpdataException exception,
                                  HttpServletRequest request, HttpServletResponse response) {

        String viewName = "system-error";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 捕获登录异常,并设置对应的跳转页面
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(LoginFailedException exception,
                      HttpServletRequest request, HttpServletResponse response) {
        String viewName = "admin-login";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     *
     * @param exception  实际捕获到的异常类型
     * @param request    当前请求对象
     * @param response   当前响应对象
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class) //将一个具体的异常类型和一个方法关联起来
    public ModelAndView resolveNullPointerException(NullPointerException exception,
                   HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 判断请求类型,用来判断返回是页面还是json
        final boolean requestType = CrowdUtil.judgeRequestType(request);

        // 如果是Ajax请求
        if (requestType) {

            // 创建ResultEntity对象
            ResultEntity resultEntity = ResultEntity.Failed(exception.getMessage());

            // 创建Gson对象,用于将 错误信息封装的实体类resultEntity转为json
            Gson gson = new Gson();
            Object src;
            String json = gson.toJson(resultEntity);

            // 将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 由于上面已经通过原生的response对象返回了响应,所以不提供ModelAndView对象
            return null;
        }

        // 如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 将Exception对象存入模型
        modelAndView.addObject("exception", exception);

        // 设置对应的视图名称
        modelAndView.setViewName("system-error");

        return modelAndView;
    }

    /**
     *
     * @param viewName  要跳转的页面
     * @param exception 要捕获的异常
     * @param request   当前请求对象
     * @param response  当前响应对象
     * @return
     * @throws Exception
     */
    public ModelAndView commonResolve(String viewName, Exception exception,
                     HttpServletRequest request, HttpServletResponse response) {

        // 判断请求类型,用来判断返回是页面还是json
        boolean requestType = CrowdUtil.judgeRequestType(request);

        // 如果是Ajax请求
        if (requestType) {

            // 创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.Failed(exception.getMessage());


            // 创建Gson对象,用于将 错误信息封装的实体类resultEntity转为json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);

            // 将JSON字符串作为响应体返回给浏览器
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 由于上面已经通过原生的response对象返回了响应,所以不提供ModelAndView对象
            return null;
        }
        // 如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        // 设置对应的视图名称
        modelAndView.setViewName(viewName);

        return modelAndView;
    }
}
