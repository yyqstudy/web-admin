package com.example.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.bean.User;
import com.example.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;
import java.util.List;

/**
 * 用来处理table下面的页面跳转
 */
@Controller
public class TableController {

    //🗒️ mybatisPlus-crud操作，注入userservice
    @Autowired
    UserService userService;

    //点击Basic Table会来到basic_table.html页面
    @GetMapping("/basic_table")
    public String basic_table(){

        //测试错误处理  -- Cookie: JSESSIONID=4A3AB99A70F42629BD589F044285B574
        int i = 10/0;
        return "table/basic_table";
    }

    //定义mybatisplus  dynamic_table页面删除方法
    //删除之后要URL显示哪一页 @RequestParam(value="pn",defaultValue = "1")Integer pn
    //重定向参数 RedirectAttributes 添加了属性pn，自动添加到URL（redirect：）

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value="pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){

        userService.removeById(id);

        //重定向参数添加一个属性
        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }

    //来到dynamic_table页面
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value="pn",defaultValue = "1")Integer pn, Model model){
        /*//表格内容的遍历--例子：User为例
        //创建User对象
        List<User> users = Arrays.asList(new User("alex","11111"),
                      new User("alisa","11333"),
                       new User("lucy","aaabb"),
                        new User("sylia","77777"));
        //把User对象放在model里面（也就是请求域里面）: users是List<User> users,赋值给"users"
        model.addAttribute("users",users);*/

        //🗒️mybatisPlus ：从数据库中查出user表中用户进行展示
        // 查询
        //userService.getAll();
        List<User> list = userService.list();
        //为了方便，放在model里面遍历
        model.addAttribute("users",list);

        /**
         * 分页查询 Page<T>(current,size)
         * current是当前页码,不能写死，要通过 @RequestParam(value="pn",defaultValue = 1)获取
         *  public String dynamic_table(@RequestParam(value="pn",defaultValue = "1")Integer pn, Model model){}
         *
         *  size是每页显示的数
         *  分页对象：usersPage ，查询条件：null
         */


        Page<User> userPage = new Page<User>(pn, 2);//⚠️Page要导的是mybatisplus，别导错包
        //page就是分页查询结果，所有数据放在page对象里面
        IPage page = userService.page(userPage, null);
        long current = page.getCurrent();//获取当前页
        long pages = page.getPages();//总计多少页
        long total = page.getTotal();//总计多少条记录
        List records = page.getRecords();//查出数据库数据

        model.addAttribute("page",page);

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
