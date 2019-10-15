package com.bjq.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//添加在容器中
@Component
//自定义我们的ErrorAttributes
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("Logo","bjq");
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        //然后再把ext一起添加到我们自己的map里面
        map.put("ext",ext);
        return map;
    }
}
