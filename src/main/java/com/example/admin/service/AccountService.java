package com.example.admin.service;

import com.example.admin.bean.Account;
import org.springframework.stereotype.Service;


public interface AccountService {

    Account getAcctByid(Long id);
}
