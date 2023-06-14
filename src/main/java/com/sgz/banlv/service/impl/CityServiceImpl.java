package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgz.banlv.entity.City;
import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.mapper.CityMapper;
import com.sgz.banlv.mapper.SceniczoneMapper;
import com.sgz.banlv.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private SceniczoneMapper sceniczoneMapper;

    //通过城市名称获取城市信息
    @Override
    public Result<City> GetCityInfo(String city_name) {
        City city = this.query().eq("city_name", city_name).last("LIMIT 1").one();
        return Optional.ofNullable(city)
                .map(Result::success)
                .orElse(Result.failure());
    }

    //获取热门城市信息
    @Override
    public Result<List<City>> GetPopularCities() {
        IPage<City> cityPage = new Page<>(1,5);

        List<City> records = cityMapper.selectPage(cityPage, null).getRecords();
        return !records.isEmpty() ? Result.success(records) : Result.failure();
    }


    //    通过传入的city_name模糊查询获取城市信息(城市主键、城市名、景区数)
    @Override
    public Result<Map<String, Object>> FuzzyQueryCityInfo(String city_name) {
        Map<String, Object> result = new HashMap<>();
        City city = null;
        int count = 0;
        city = this.lambdaQuery().eq(City::getCity_name, city_name).last("LIMIT 1").one();

        // 通过城市名获取城市信息
//        QueryWrapper<City> cityQueryWrapper = new QueryWrapper<>();
//        cityQueryWrapper.lambda().eq(City::getCity_name, city_name).last("LIMIT 1");
//        city = cityMapper.selectOne(cityQueryWrapper);

        if (city != null) {
            // 存在城市信息，获取景区数
            QueryWrapper<Sceniczone> szQuery = new QueryWrapper<>();
            szQuery.lambda().eq(Sceniczone::getCity_id, city.getCity_id());
            count = sceniczoneMapper.selectCount(szQuery).intValue();
        } else {
            // 不存在城市信息，则进行模糊查询
            QueryWrapper<City> fuzzyCityQueryWrapper = new QueryWrapper<>();
            fuzzyCityQueryWrapper.lambda().like(City::getCity_name, city_name).last("LIMIT 1");
            city = cityMapper.selectOne(fuzzyCityQueryWrapper);

            if (city != null) {
                QueryWrapper<Sceniczone> szQuery = new QueryWrapper<>();
                szQuery.lambda().eq(Sceniczone::getCity_id, city.getCity_id());
                count = sceniczoneMapper.selectCount(szQuery).intValue();
            }
        }

        result.put("count", count);
        result.put("city", city);

        return Result.success(result);
    }
}
