package com.example.admin;


import com.example.admin.bean.User;
import com.example.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
      //jdbcTemplate.queryForObject("select * from account_tb")


        //表中如果有多条记录查询
       //jdbcTemplate.queryForList("select * from account_tb")

        //统计表中有多少条记录--count(*)
        //查出的数据是一个long类型
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_db",Long.class);
        log.info("记录总数: {}", aLong);


        //验证数据源类型是不是我们自定义的DruidDataSource
        log.info("数据源类型: {}",dataSource.getClass());

    }

    /**
     * 测试mybatis-plus usermapper是不是有增删改查的方法
     * 要记得自动注入UserMapper
     */
    @Autowired
    UserMapper userMapper;

    @Test
    void testUserMapper(){
        //查询1号用户
        User user = userMapper.selectById(1L);
        log.info("用户信息：{}",user);
    }




}
