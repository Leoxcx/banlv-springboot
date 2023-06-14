package com.sgz.banlv.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgz.banlv.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 城市 Mapper 接口
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {


    /**
     * 通过cityName查询city_id
     *
     * @return
     */
//    Integer GetIdByName(String cityName);

    /**
     * 通过city_id查询所有该城市的景区数
     *
     * @return
     */
//    Integer searchTotalCountByCityId(@Param(value="city_id") int city_id);

    /**
     * 通过cityName模糊查询所有对应城市
     *
     * @return
     */
//    City searchAllByCityName(@Param(value="cityName")  String cityName);
}
