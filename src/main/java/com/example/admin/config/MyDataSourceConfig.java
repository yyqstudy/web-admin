package com.example.admin.config;

//创建数据源配置文件 -- druid


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;

@Deprecated //表示过时了
//@Configuration
public class MyDataSourceConfig {

    //给IOC容器放数据源--是自己创建的数据源

    /**
     * @ConditionalOnMissingBean(DataSource.class):
     *默认自动配置是判断容器中没有才会配自定义的
     *
     * @ConfigurationProperties("spring.datasource"):
     * 说明MyDataSourceConfig.java中 DuridDataSource 里面的属性和application.yaml文件 spring的datasource一一绑定
     * 绑定之后去测试
     */

   /* @ConfigurationProperties("spring.datasource")
    @Bean*/
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        //写死了，还是去配置文件那里配置application.yaml
//        druidDataSource.setUrl();
//        druidDataSource.setUsername();
//        druidDataSource.setPassword();

       /**
        * 打开druid监控统计功能--StatFilter--SQL监控
        * druidDataSource.setFilters("stat");
        *
        * 添加防火墙：druidDataSource.setFilters("stat",wall");
         */
        druidDataSource.setFilters("stat,wall");
       return druidDataSource;
    }

    /**
     * 配置druid的监控页功能--StatViewServlet
     */
//    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<StatViewServlet>(statViewServlet, "/druid/*");

        /**
         * 自定义：监控页面需要账号密码登录，并且限定哪些地址能登录
         */
        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","12345");
        return registrationBean;
    }

    /**
     * WebStatFilter 用于采集web-jdbc关联监控的数据。
     * 监控Web应用
     */
//    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(webStatFilter);
        //不能拦截的路径
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        //设置拦截路径
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }



}
