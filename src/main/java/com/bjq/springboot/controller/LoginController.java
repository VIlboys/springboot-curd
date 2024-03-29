package com.bjq.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
   // @RequestMapping(,method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session)
    {
        if(!StringUtils.isEmpty(username) && "123456".equals(password))
        {
            //登陆成功,防止表单提交我们可以使用重定向
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        else
        {
            map.put("maps","登陆失败，密码或者账号错误!");
            return "login";
        }
    }

}
