package com.example.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 这个拦截器作用是登录检查
 *   1、配置好拦截器要拦截哪些请求
 *   2、把这些配置放在容器中---写一个配置文件，用来定制SpringMVC一些功能
 *
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     *
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 打印日志，看一下拦截的请求路径有哪些
         */
        String requestURI = request.getRequestURI();
        log.info("preHandle拦截的请求路径是{}",requestURI );

        //登录检查
        //拿到session对象
        HttpSession session = request.getSession();
        //检查session里面有没有登录用户 -- 在IndexController中session.setAttribute("logUser",user);
        Object loginUser = session.getAttribute("logUser");

        if(loginUser != null){
            //放行
            return true;
        }
        //loginUser为空则拦截:未登录，回到登录页面
        //但是因为重定向取不出错误提示信息：session.setAttribute改为request.setAttribute
        request.setAttribute("msg","请先登录");//给login.html中<label style="color:red " th:text="${msg}"></label>
        //response.sendRedirect("/");
        //拿到request的转发器，转发到"/"，然后forward，把原生的request,response都转过去
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    /**
     * 目标方法执行之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //打印日志
        log.info("postHandle执行{}",modelAndView);
    }

    /**
     * 页面渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //打印日志，看看有没有异常
        log.info(" afterCompletion执行异常{}",ex);
    }
}
