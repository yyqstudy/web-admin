package com.example.admin.mapper;

import com.example.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * AccountMappper 专门操作数据库的表account_db
 * 在这里写两个方法
 *   返回账号信息 在bean下面创建实体类Account,创建完成回来这里写方法getAcct()
 *   方法实现要在xml文件中，在resource下面的mapper下面准备一个SQL映射文件AccountMapper.xml
 */
@Mapper
public interface AccountMapper {

    public Account getAcct(Long id);
}
