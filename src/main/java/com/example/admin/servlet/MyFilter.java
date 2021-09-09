package com.example.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns={"/css/*","/images/*"})//现在拦截所有static静态资源下面的部分路径
public class MyFilter implements Filter {
    //Filter初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter初始化完成");
    }


    //在doFilter里面写逻辑
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      log.info("MyFilter开始工作");
      //放行--用filter链
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //销毁
    @Override
    public void destroy() {
       log.info("MyFilter销毁");
    }
}
