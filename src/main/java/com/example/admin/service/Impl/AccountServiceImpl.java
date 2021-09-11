package com.example.admin.service.Impl;

import com.example.admin.bean.Account;
import com.example.admin.mapper.AccountMapper;
import com.example.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl  implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    public Account getAcctByid(Long id){
        return accountMapper.getAcct(id);
    }
}






















/*package com.example.admin.service.Impl;

        import com.example.admin.bean.Account;
        import com.example.admin.mapper.AccountMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

*/
/**
 * 用来调用AccountMapper.java
 */
/*@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAcctByid(Long id){
        return accountMapper.getAcct(id);
    }
}*/