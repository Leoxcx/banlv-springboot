package com.sgz.banlv.service;

import com.sgz.banlv.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface ICityService extends IService<City> {

    Result<City> GetCityInfo(String city_name);

    Result<List<City>> GetPopularCities();

    Result<Map<String, Object>> FuzzyQueryCityInfo(String city_name);
}
