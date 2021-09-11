package com.example.admin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 断言测试
 */


public class Junit5AssertionTest {

    /**
     * 简单测试  用来对单个值进行简单的验证
     * assertEquals    判断两个对象或两个原始类型是否相等
     * assertNotEquals 判断两个对象或两个原始类型是否不相等
     * assertSame      判断两个对象引用是否指向同一个对象
     * assertNotSame   判断两个对象引用是否指向不同的对象
     * assertTrue      判断给定的布尔值是否为 true
     * assertFalse     判断给定的布尔值是否为 false
     * assertNull      判断给定的对象引用是否为 null
     * assertNotNull   判断给定的对象引用是否不为 null
     *
     */

    /**
     * 前面的断言测试失败，后面的断言代码不会执行
     */
    @DisplayName("测试简单断言")
    @Test
    void tsetSimpleAssertions(){
        int cal = cal(2, 3);
        Assertions.assertEquals(5,cal,"业务逻辑计算失败");//如果不相等，就会显示错误信息

        Object o1 = new Object();
        Object o2 = new Object();
        assertNotSame(o1,o2,"两个对象不一样");
    }

    //写一个业务逻辑
    int cal(int i , int j){
        return i+j;
    }

    //测试数组是否相等
    @DisplayName("array assertions")
    @Test
    void array(){
        assertArrayEquals(new int[]{1,2},new int[]{1,2});
    }

    //测试组合断言
    @Test
    @DisplayName("组合断言")
    void all() {
        /**
         * 所有断言都成功才可以往下执行
         */
        assertAll("test",
                () -> assertTrue(true && true, "结果不为true"),
                () -> assertEquals(1, 1, "结果不是1"));
        System.out.println("============");
    }

    //测试异常断言
    @DisplayName("异常断言")
    @Test
    void testException(){
        //认为business logic一定出异常，没有异常才能提示信息
        assertThrows(ArithmeticException.class,() ->{int i = 10/0;} ,"业务逻辑居然正常???");

    }

    //快速失败
    @Test
    @DisplayName("快速失败")
    void testFail() {

        if (1 == 2) {
            fail("测试失败");
        }
    }


    /**
     *  测试前置条件assumption
     *
     *
     */
    @Test
    @DisplayName("前置条件assumption")
    void testAssumption(){
        //如果是true，console才会输出111111,不然报告显示会跳过这个测试
        Assumptions.assumeTrue(false,"结果不是true");
        System.out.println("111111");
    }

}
