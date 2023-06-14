package com.sgz.banlv.controller;

import com.sgz.banlv.entity.City;
import com.sgz.banlv.service.ICityService;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
public class CityController {

    @Resource
    private ICityService cityService;

    //通过城市名称获取城市信息
    @RequestMapping("/getcityinfo")
    public Result<City> GetCityInfo(@RequestParam String city_name) {
        if(StringUtils.isNotEmpty(city_name)) {
            return cityService.GetCityInfo(city_name);
        }
        return Result.failure();
    }

    //获取热门城市信息
    @RequestMapping("/getpopularcities")
    public Result<List<City>> GetPopularCities() {
        return cityService.GetPopularCities();
    }

//    通过传入的city_name模糊查询获取城市信息
    @RequestMapping("/fuzzyquerycityinfo")
    public Result<Map<String, Object>> FuzzyQueryCityInfo(@RequestParam String city_name) {
        if(StringUtils.isNotEmpty(city_name)) {
            return cityService.FuzzyQueryCityInfo(city_name);
        }
        return Result.failure();
    }
}
