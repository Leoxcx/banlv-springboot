package com.sgz.banlv.service;

import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.entity.Scenicspot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IScenicspotService extends IService<Scenicspot> {

    Result<List<ScenicResource>> ScenicSpotRecommend(Long scenicSpot_id);

    Result<Integer> GetScenicSpotPlayRecord(Long resource_id);

    Result<List<ScenicResource>> GetResourcesByScenicSpotId(Long scenicSpot_id);

    Result<Scenicspot> SearchTotalInfo(Long scenicSpot_id);

    Result<List<Scenicspot>> GetAllScenicSpotByScenicSpotId(Long scenicSpot_id);
}
