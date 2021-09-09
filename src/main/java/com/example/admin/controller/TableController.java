package com.example.admin.controller;

import com.example.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jnlp.UnavailableServiceException;
import java.util.Arrays;
import java.util.List;

/**
 * 用来处理table下面的页面跳转
 */
@Controller
public class TableController {

    //点击Basic Table会来到basic_table.html页面
    @GetMapping("/basic_table")
    public String basic_table(){

        //测试错误处理  -- Cookie: JSESSIONID=4A3AB99A70F42629BD589F044285B574
        int i = 10/0;
        return "table/basic_table";
    }

    //来到dynamic_table页面
    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        //表格内容的遍历--例子：User为例
        //创建User对象
        List<User> users = Arrays.asList(new User("alex","11111"),
                      new User("alisa","11333"),
                       new User("lucy","aaabb"),
                        new User("sylia","77777"));
        //把User对象放在model里面（也就是请求域里面）: users是List<User> users,赋值给"users"
        model.addAttribute("users",users);
        return "table/dynamic_table";
    }


    //来到responsive_table页面
    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    //来到editable_table页面
    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }



}
