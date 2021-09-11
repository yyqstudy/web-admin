package com.example.admin.mapper;

import com.example.admin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 操作数据库中表City的mapper接口
 */
//@Mapper
public interface CityMapper {

    //根据id查询城市
    // 纯注解版不用像查询account_db一样要新建一个AccountMapper，然后在里面写SQL语句
    @Select(" select * from city where id=#{id}")
    public City getById(Long id);


    //插入--sql语句很麻烦--准备一个对应的mapper.xml文件--CityMapper.xml
   @Insert("insert into city(`name`,`state`,`country`)values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys= true,keyProperty="id")
    public void insert(City city);


}
