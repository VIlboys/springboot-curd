package com.bjq.springboot.config;

import com.bjq.springboot.filter.Myfilter;
import com.bjq.springboot.listener.MyListener;
import com.bjq.springboot.serlvet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

@Configuration
public class MySerlvetConfig {

    //注册自己的serlvet
    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet()
    {
        ServletRegistrationBean registrationBean = new
                ServletRegistrationBean(new MyServlet(),"/mySerlvet");
        return  registrationBean;
    }
    //注册自己的Filter
    @Bean
    public FilterRegistrationBean myFilter()
    {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Myfilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet","/*"));

        return registrationBean;
    }
    //注册自己的listener
    @Bean
    public ServletListenerRegistrationBean mylistener()
    {
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }


    //在springboot2.x以后WebServerFactoryCustomizer代替了EmbeddedServletContainerCustomizer以下写法是2.x以上写法
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8082);
            }
        };
    }
}
