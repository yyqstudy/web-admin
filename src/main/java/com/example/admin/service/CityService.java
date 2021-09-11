package com.example.admin.service;

import com.example.admin.bean.City;

public interface CityService {
     City getById(long id);

     void saveCity(City city);
}
