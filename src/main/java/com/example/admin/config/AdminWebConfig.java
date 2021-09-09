package com.example.admin.config;

import com.example.admin.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 定制SpringMVC一些功能
 *
 *   1、编写一个拦截器实现HandlerInterceptor接口
 *   2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 *   3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 *
 *   SpringBoot定制化组件
 *   @EnableWebMvc:全面接管  可以全面接管SpringMVC，所有规则全部自己重新配置
 *        1、静态资源、视图解析器、欢迎页.....全部失效
 *
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    /**
     * 定义静态资源行为
     * addResourceHandler() 资源处理
     * addResourceLocations() 资源路径
     * 访问 /aa/** 所有请求都去 classpath:/static/ 下面进行匹配
     */
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/aa/**")
                .addResourceLocations("classpath:/static/");
    }
*/
    //把拦截器写在这里面,才能工作

    /**
     * addInterceptor():这是添加自己写的拦截器
     * addPathPatterns():这是告诉我们要拦截哪些路径，但是"/","/login"要放行，因为即使没登录或者登录不成功，也要让大众访问到的
     * excludePathPatterns():要放行的路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//所有请求被拦截，包括静态资源：把登录页面css格式都拦截了
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/aa/**"/*,"/sql"*/);//放行的请求要包括静态资源
    }

    /**
     * SpringBoot定制化组件
     * 修改底层组件
     */
    /*@Bean
    public WebMvcRegistrations webMvcRegistrations(){
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return null;
            }
        };
    }*/
}
