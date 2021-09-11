package com.example.admin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ValueSource: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型
 * @NullSource: 表示为参数化测试提供一个null的入参
 * @EnumSource: 表示为参数化测试提供一个枚举入参
 * @CsvFileSource：表示读取指定CSV文件内容作为参数化测试入参
 * @MethodSource：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)
 */

@DisplayName("参数化测试")
public class TestingParamDemo {


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void testParameterized(int i){
        System.out.println(i);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testParameterized02(String i){
       System.out.println(i);
   }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }
}
