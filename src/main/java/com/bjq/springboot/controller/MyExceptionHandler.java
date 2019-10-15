package com.bjq.springboot.controller;

import com.bjq.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//如果是一个异常处理必须写上ControllerAdvice
public class MyExceptionHandler {

    //浏览器和客户端都是返回json
   /* @ResponseBody
   @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;
    }*/


    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request)
    {
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码
        //Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());

        //我们把map放进ext
        request.setAttribute("ext",map);
        return "forward:/error";
    }



}
