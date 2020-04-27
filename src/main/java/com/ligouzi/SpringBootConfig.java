package com.ligouzi;

import com.ligouzi.interceptors.ProtalLoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class SpringBootConfig implements WebMvcConfigurer {

    @Autowired
    ProtalLoginCheckInterceptor protalLoginCheckInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> includeUrl=new ArrayList<>();
        includeUrl.add("/portal/user/**");
        includeUrl.add("/portal/cart/**");

        List<String> excludeUrl=new ArrayList<>();
        excludeUrl.add("/portal/user/login.do");
        excludeUrl.add("/portal/user/register.do");
        registry.addInterceptor(protalLoginCheckInterceptor).addPathPatterns(includeUrl) //添加需要拦截的路径
                .excludePathPatterns(excludeUrl); //排除不需要拦截的路径

    }
}
