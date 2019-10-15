package com.bjq.springboot.config;

import com.bjq.springboot.component.LoginHandlerInterceptor;
import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 以前是1.5.9版本使用的是extends exWebMvcConfigurerAdapter
 *
 * 如果是2.1.9版本请implements WebMvcConfigurer
 */
//@EnableWebMvc  不要全部接管springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {




    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //第一个是url表示是什么请求,后面是请求到什么页面
        //下面所写的意思是：在bjq这个请求下同样是来到成功页面
        registry.addViewController("/bjq").setViewName("success");

        //测试在这里能不能返回到login页面?不能
       // registry.addViewController("/").setViewName("login");
        //registry.addViewController("index.html").setViewName("login");
    }

    @Bean //将组件注册在容器
    public WebMvcConfigurer webMvcConfigurer()
    {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("index.html").setViewName("login");
                registry.addViewController("main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //springboot已经把静态资源映射好了
                //registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        //excludePathPatterns的意思是不包括那些请求
                       // .excludePathPatterns("/","/index.html","/user/login");
            }
        };
        return configurer;
    }


}
