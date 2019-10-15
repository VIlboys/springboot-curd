package com.bjq.springboot.controller;

import com.bjq.springboot.exception.UserNotExistException;
import com.bjq.springboot.pojo.User;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class HelloController {



 /*   @RequestMapping({"/","/index.html"})
    public String index()
    {
        return "index";
    }*/


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user)
    {
        if(user.equals("aaa"))
        {
            throw new UserNotExistException();
        }
        return "hello World!";
    }

    @RequestMapping("/success")
    public String success(Model model)
    {
        model.addAttribute("msg","使用thymeleaf进行打印输出");
        return "success";
    }
    @RequestMapping("/success01")
    public String success01(Map<String,Object> map)
    {
        map.put("map","<h1>map内容</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","王五"));
        return "success";
    }

    @RequestMapping("show2")
    public String show2(Model model)
    {
        model.addAttribute("sex","男");
        model.addAttribute("id",2);
        return"success";
    }


    @RequestMapping("/showUserAll")
    public String getUserAll(Model model)
    {
        List<User> list = new ArrayList<>();
        list.add(new User(1,"lisi",25));
        list.add(new User(1,"wangwu",15));

        model.addAttribute("list",list);

        return "getUser";
    }

    @RequestMapping("/AllUserMap")
    public String AllUserMap(Model model)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("u1",new User(2,"爸爸",17));
        map.put("u2",new User(3,"六航母",18));
        map.put("u3",new User(4,"牢狱",19));
        map.put("u3",new User(5,"裙子",19));
        map.put("u3",new User(6,"谢志文",19));

        model.addAttribute("map",map);

        return "getUser";
    }




}
