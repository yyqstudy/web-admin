package com.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.bean.User;

/**
 * IService 顶级service接口
 * IService<T> :表示service要查询哪些数据类型 比如这里是要查询User
 *
 */
public interface UserService extends IService<User> {
}
