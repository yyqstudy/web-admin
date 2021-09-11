package com.example.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@TableName("user_tb1")
public class User {
    /**
     * 由于下面写的所有属性都应该在数据库中，所以比如password 这些需要用注解说明是在数据库的表中不存在的 @TableField(exist=false)
     *
     * */
    //这是在IndexController判断登录名和密码要等于12345才能登录，
    // 即使在练习MybatisPlus不能删，只是在下面加数据库字段
    @TableField(exist=false)
    private String userName;
    @TableField(exist=false)
    private String password;
    //然后去login.html的form表单，添加userName和password


    //这是数据库user表的字段
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
