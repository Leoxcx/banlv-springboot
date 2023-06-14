package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.entity.ScenicspotResource;
import com.sgz.banlv.mapper.ScenicspotResourceMapper;
import com.sgz.banlv.service.IMiddleTableService;
import com.sgz.banlv.service.IScenicspotResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.service.IScenicspotService;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
public class ScenicspotResourceServiceImpl extends ServiceImpl<ScenicspotResourceMapper, ScenicspotResource> implements IScenicspotResourceService {

    @Resource
    private ScenicspotResourceMapper scenicspotResourceMapper;
    @Resource
    private IMiddleTableService middleTableService;

    @Override
    public Result<List<ScenicspotResource>> GetScenicResourceInfo(Long scenicSpot_id) {
        List<ScenicspotResource> scenicspotResources = scenicspotResourceMapper.selectList(new QueryWrapper<ScenicspotResource>()
                .eq("scenicSpot_id", scenicSpot_id)
                .eq("scenicSpot_resource_use", true));
//        List<ScenicspotResource> scenicspotResources = scenicspotResourceMapper.selectAllByScenicSpot_idAndScenicSpot_resource_useTrue(scenicSpot_id);
//        List<ScenicspotResource> scenicspotResources = middleTableService.spotToResource(scenicSpot_id);
        return !scenicspotResources.isEmpty() ? Result.success(scenicspotResources) : Result.failure();
    }
}
