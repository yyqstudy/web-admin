package com.example.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*@ServletComponentScan(basePackages = "com.example.admin")表示com.example.admin下面所有文件只要有Servlet注解，都可以被扫描到*/
@ServletComponentScan(basePackages = "com.example.admin")
@SpringBootApplication
@MapperScan("com.example.admin.mapper") //com.example.admin.mapper下面的mapper都被扫描到，其他的接口就可以不用标注@Mapper注解
public class WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }

}
