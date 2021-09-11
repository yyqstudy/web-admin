package com.example.admin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;
//这个练习重点看控制台输出结果

/**但是要使用springboot里面的容器内容，就要加上注解@SpringBootTest
 * @SpringBootTest里面：
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith({SpringExtension.class})
 */

@SpringBootTest
@DisplayName("Junit5Test功能测试")
public class Junit5Test {
    /**
     *
     * @DisplayName :为测试类或者测试方法设置展示名称
     * @BeforeEach :表示在每个单元测试之前执行
     * @AfterEach :表示在每个单元测试之后执行
     * @BeforeAll :表示在所有单元测试之前执行
     * @AfterAll :表示在所有单元测试之后执行
     * @Tag :表示单元测试类别，类似于JUnit4中的@Categories
     * @Disabled :表示测试类或测试方法不执行，类似于JUnit4中的@Ignore
     * @Timeout :表示测试方法运行如果超过了指定时间将会返回错误
     * @ExtendWith :为测试类或测试方法提供扩展类引用
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("测试Displayname注解")
    @Test
    void testDisplayname(){
        System.out.println(1);
        System.out.println(jdbcTemplate);
    }

    @Disabled //禁用该方法
    @DisplayName("测试方法2")
    @Test
    void test2(){
        System.out.println(2);
    }

    //重复测试5次
    //@RepeatedTest(5)
    @Test
    void test3(){
        System.out.println(3);
    }


    //规定方法超时时间，超出时间测试出异常
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);//要休眠600毫秒，所以必定超时
    }


    @BeforeEach
    void testBeforeEach(){
        System.out.println("测试就要开始了.....");

    }

    @AfterEach
    void testAfterEach(){
        System.out.println("测试结束了....");
    }

    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试就要开始了.....");
    }

    @AfterAll
    static void testAfterall(){
        System.out.println("所有测试已经结束了.....");
    }
}
