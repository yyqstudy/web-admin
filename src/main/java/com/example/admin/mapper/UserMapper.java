package com.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.bean.User;

/**
 * BaseMapper里面已经有增删改查方法，所以extend就可以了，不用像AccountMapper和CityMapper要自己写
 */
public interface UserMapper extends BaseMapper<User> {
}
