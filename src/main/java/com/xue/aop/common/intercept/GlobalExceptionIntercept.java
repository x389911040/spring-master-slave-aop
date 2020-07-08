package com.xue.aop.common.intercept;

import com.alibaba.fastjson.JSONObject;
import com.xue.aop.common.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * @author xuejh
 * @description 全局异常拦截器
 * @create 2020-04-07 17:23
 **/
@ControllerAdvice(basePackages = "com.xue.aop")
@Log4j2
public class GlobalExceptionIntercept {

    @ExceptionHandler(Exception.class)
    public void errorMsg (HttpServletRequest request, HttpServletResponse response, Exception ex) {

        log.info("错误链接"+request.getRequestURL().toString());
        ex.printStackTrace();
        String errorMessage = "系统异常";
        if (ex instanceof BusinessException) {
            errorMessage = ex.getMessage();
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "false");
        jsonObject.put("message", errorMessage);

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
