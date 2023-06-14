package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.common.model.PlayNum;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.mapper.ScenicspotMapper;
import com.sgz.banlv.service.IMiddleTableService;
import com.sgz.banlv.service.IScenicspotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class ScenicspotServiceImpl extends ServiceImpl<ScenicspotMapper, Scenicspot> implements IScenicspotService {


    @Resource
    private IMiddleTableService middleTableService;

    //景点scenicSpot_id获取资源（前十个）
    @Override
    public Result<List<ScenicResource>> ScenicSpotRecommend(Long scenicSpot_id) {


        //    景点id找当前景点的10个资源resource
        List<ScenicResource> scenicResources = middleTableService.spotToResourceLimit(scenicSpot_id);

        if(CollectionUtils.isNotEmpty(scenicResources)) {
            return Result.success(scenicResources);
        }
        return Result.failure();
    }

    //获取资源对应景点播放记录
    @Override
    public Result<Integer> GetScenicSpotPlayRecord(Long resource_id) {
        //通过资源id查景点id
        Long scenicSpot_id = middleTableService.getScenicspotIdByResourceId(resource_id);
        if(scenicSpot_id != null) {
            //获取资源对应景点播放记录
            int scenicSpotRecord = PlayNum.getPlayNumModel().getScenicSpotRecord(scenicSpot_id);
            if(scenicSpotRecord != 0) {
                return Result.success(scenicSpotRecord);
            }
        }

        return Result.failure();
    }

    //通过景点id获取对应景点资源列表
    @Override
    public Result<List<ScenicResource>> GetResourcesByScenicSpotId(Long scenicSpot_id) {

        // 查找景点资源中间表
        List<ScenicResource> scenicResources = middleTableService.spotToResource(scenicSpot_id);

        return !scenicResources.isEmpty() ? Result.success(scenicResources) : Result.failure();
    }

    //通过景点id获取景点信息
    @Override
    public Result<Scenicspot> SearchTotalInfo(Long scenicSpot_id) {
        Scenicspot scenicspot = this.getById(scenicSpot_id);
        return Optional.ofNullable(scenicspot)
                .map(Result::success)
                .orElse(Result.failure());
    }

    //通过景点id获取当前景区所有的景点信息
    @Override
    public Result<List<Scenicspot>> GetAllScenicSpotByScenicSpotId(Long scenicSpot_id) {
        Long sceniczoneId = middleTableService.getSceniczoneIdByScenicspotId(scenicSpot_id);
        List<Scenicspot> scenicspots = middleTableService.scenicZoneSpotToScenic(sceniczoneId);

        return !scenicspots.isEmpty() ? Result.success(scenicspots) : Result.failure();
    }


}
