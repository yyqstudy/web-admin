package com.example.admin.bean;

import lombok.Data;

/**
 * 下面三个属性是看着SQL中的account_db里面的字段来写  int---Long  varchar---String
 */
@Data
public class Account {
    private Long id;
    private String name;
    private String country;

}
