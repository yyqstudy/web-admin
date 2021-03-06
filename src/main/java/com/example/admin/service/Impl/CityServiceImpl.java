package com.example.admin.service.Impl;

import com.example.admin.bean.City;
import com.example.admin.mapper.CityMapper;
import com.example.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//业务逻辑组件  新建一个service调用CityMapper,然后让Mapper去数据库根据要求找数据
@Service
public class CityServiceImpl implements CityService {

    //自动注入CityMapper
    @Autowired
    CityMapper cityMapper;

    public City getById(long id){
        return cityMapper.getById(id);
    }

    public void saveCity(City city){
        cityMapper.insert(city);
    }




   /* //自动注入CityMapper
    @Autowired
    CityMapper cityMapper;

    public City getById(long id){
        return cityMapper.getById(id);
    }

    public void saveCity(City city){
        cityMapper.insert(city);
    }*/
}
