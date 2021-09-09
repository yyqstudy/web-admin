package com.example.admin.controller;


import com.example.admin.bean.Account;
import com.example.admin.bean.City;
import com.example.admin.bean.User;
import com.example.admin.service.AccountService;
import com.example.admin.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 负责页面跳转
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CityService cityService;

    @RequestMapping("/goCity")
    public String goCity(){

        return "/city";
    }

    //测试mybatis 注解和配置混合版  insert
    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){
        cityService.saveCity(city);
        return city;
    }


    //测试mybatis 纯注解版 看一下能不能获取表city数据
    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
        return cityService.getById(id);
    }



    //测试mybatis配置的方法 看一下能不能获取表account_db数据
    @ResponseBody
    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Long id){

        //账号内容信息通过接口文件AccountMapper.java得到的--可以自动注入 --但是开发是用service调用的
        //controller调用service（自动注入），service  调用mapper
        //id从哪里拿，可以你发一个请求参数过来，要哪个id，我就去数据库里面查
        return accountService.getAcctByid(id);
    }

    //测试监控页面 查询数据库 数据响应出去就不跳转页面
    //效果：访问/sql，直接可以看到监控页面中SQL监控有内容
    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_db",Long.class);
        return aLong.toString();
    }

    //发请求去登录页面
    @GetMapping(value = {"/","/login"})
    public String loginPage(){


        return "login";
    }

    /**
     * 检查是否登录
     */
    //输入账号密码登录来到主页main.html
    //post提交表单
    @PostMapping("/login")
    //String username,String password封装成Class User ，登录成功把它们放在Session里面
    //转发回到登录页面，所以把登录页面要去的所有内容放在Model里面
    public String main(User user, HttpSession session, Model model){
        //判断userName和password都不为空，就说明登录成功 StringUtils.hasLength(user.getPassword())
        if(StringUtils.hasLength(user.getUserName()) && "12345".equals(user.getPassword())){
            //把登录成功的用户存起来
            session.setAttribute("logUser",user);

            //解决重复提交表单问题：redirect重定向--redirect:/main.html-- @GetMapping("/main.html")
            //以前登录成功是转发页面，现在是登录成功重定向到main.html
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","账号或密码错误");
            //登录失败，回到登录页面
            return "login";
        }


    }


    /**
     * 去main页面
     * 来到后台页面要作判断，是否登录了，防止没有登录的就直接访问到main页面
     * 如果写有拦截器，在这里就不用检查是否登录了
     * @return
     */
    //直接访问不了templates文件夹下面的main.html，必须经过请求处理--模板引擎解析
    //直接访问只能访问在static文件夹下面的
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){

        //打印日志：看看拦截器的postHandle()
        log.info("当前方法是：{}","mainPage");
     /*  //是否登录
        Object logUser = session.getAttribute("logUser");//logUser--是上一个方法： 把登录成功的用户存起来 session.setAttribute("logUser",user);
        if(logUser != null){
            return "main";

        }else{
            //回到登录页面
            //提示信息
            model.addAttribute("msg","请重新登录");
            return "login";
        }*/
        return "main";

    }


}
