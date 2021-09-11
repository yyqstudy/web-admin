package com.example.admin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.bean.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl<M extends BaseMapper<T>, T>
 *     M extends BaseMapper<T> 表示当前的service要操作哪张表，用哪个mapper
 *     T 要返回哪些数据类型
 *   这样extends之后，UserServiceImpl里面什么方法都不用写
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
