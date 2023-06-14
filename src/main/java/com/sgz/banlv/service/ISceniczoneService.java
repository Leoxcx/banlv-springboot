package com.sgz.banlv.service;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.dto.ScenicSpotInfoDto;
import com.sgz.banlv.entity.Sceniczone;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 景区 服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface ISceniczoneService extends IService<Sceniczone> {

    Result<Integer> GetScenicZonePlayRecord(Long resource_id);

    Result<List<ScenicSpotInfoDto>> GetScenicSpotInfoByZoneId(Long scenicZone_id) throws IllegalAccessException;

    Result<List<Sceniczone>> GetPopularScenicZone(Integer city_id, Integer currentPage);

    Result<List<Sceniczone>> FuzzyQueryCityScenicZoneInfo(String scenicZone_name, Integer city_id);

    Result<Sceniczone> SearchTotalInfo(Long scenicZone_id);
}
