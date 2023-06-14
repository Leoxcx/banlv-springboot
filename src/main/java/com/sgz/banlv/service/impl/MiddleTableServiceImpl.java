package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.entity.*;
import com.sgz.banlv.mapper.*;
import com.sgz.banlv.service.IMiddleTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MiddleTableServiceImpl implements IMiddleTableService {

    @Resource
    private ScenicspotResourceMapper scenicspotResourceMapper;

    @Resource
    private ScenicspotMapper scenicspotMapper;
    @Resource
    private ScenicResourceMapper resourceMapper;
    @Resource
    private SceniczoneScenicspotMapper sceniczoneScenicspotMapper;
    @Resource
    private SceniczoneMapper sceniczoneMapper;
    @Resource
    private UserplayMapper userplayMapper;


    //    景点id找当前景点的10个资源resource
    @Override
    public List<ScenicResource> spotToResourceLimit(long spotId) {
        List<ScenicResource> scenicResources = new ArrayList<>();
        QueryWrapper<ScenicspotResource> ssrQueryWrapper = new QueryWrapper<>();
//        查询10条符合条件的数据
        ssrQueryWrapper.eq("scenicSpot_id", spotId)
                .eq("scenicSpot_resource_use", true)
                .last("LIMIT 10");
        List<ScenicspotResource> ssrList = scenicspotResourceMapper.selectList(ssrQueryWrapper);

        if(CollectionUtils.isNotEmpty(ssrList)) {
            for (ScenicspotResource sResource : ssrList) {
                scenicResources.add(resourceMapper.selectById(sResource.getResource_id()));
            }
        }
        return scenicResources;
    }

    //    景点id找当前景点的所有资源resource
    @Override
    public List<ScenicResource> spotToResource(long spotId) {
        List<ScenicResource> scenicResources = new ArrayList<>();
        QueryWrapper<ScenicspotResource> ssrQueryWrapper = new QueryWrapper<>();
//        查询10条符合条件的数据
        ssrQueryWrapper.eq("scenicSpot_id", spotId)
                .eq("scenicSpot_resource_use", true);
        List<ScenicspotResource> ssrList = scenicspotResourceMapper.selectList(ssrQueryWrapper);

        if(CollectionUtils.isNotEmpty(ssrList)) {
            for (ScenicspotResource sResource : ssrList) {
                scenicResources.add(resourceMapper.selectById(sResource.getResource_id()));
            }
        }
        return scenicResources;
    }

    //    资源id找对应景点的信息
    @Override
    public Scenicspot resourceToScenicSpot(long resource_id) {
        Scenicspot scenicspot = new Scenicspot();
        QueryWrapper<ScenicspotResource> ssrQueryWrapper = new QueryWrapper<>();
//        通过景点资源中间表查询对应数据
        ssrQueryWrapper.eq("resource_id", resource_id)
                .eq("scenicSpot_resource_use", true)
                .last("LIMIT 1");
        ScenicspotResource scenicspotResource = scenicspotResourceMapper.selectOne(ssrQueryWrapper);

        if(scenicspotResource != null) {
//            通过ID查景点信息
            scenicspot = scenicspotMapper.selectById(scenicspotResource.getScenicSpot_id());
        }
        return scenicspot;
    }

    //    通过scenicSpotId在景区景点中间表中获取scenicZone信息
    @Override
    public Sceniczone scenicSpotToScenicZone(long scenicSpot_id) {
        Sceniczone sceniczone = new Sceniczone();
        QueryWrapper<SceniczoneScenicspot> ssQueryWrapper = new QueryWrapper<>();
//        通过景点资源中间表查询对应数据
        ssQueryWrapper.eq("scenicSpot_id", scenicSpot_id)
                .eq("scenicZone_scenicSpot_use", true)
                .last("LIMIT 1");
        SceniczoneScenicspot sceniczoneScenicspot = sceniczoneScenicspotMapper.selectOne(ssQueryWrapper);

        if(sceniczoneScenicspot != null) {
//            通过ID查景点信息
            sceniczone = sceniczoneMapper.selectById(sceniczoneScenicspot.getScenicZone_id());
        }
        return sceniczone;
    }

    //    通过景区id找到所有景点
    @Override
    public List<Scenicspot> scenicZoneSpotToScenic(long scenicZoneId) {
        List<Scenicspot> scenicSpots = new ArrayList<>();
        QueryWrapper<SceniczoneScenicspot> ssQueryWrapper = new QueryWrapper<>();
        ssQueryWrapper.eq("scenicZone_id", scenicZoneId)
                        .eq("scenicZone_scenicSpot_use",true);
        List<SceniczoneScenicspot> sceniczoneScenicspots = sceniczoneScenicspotMapper.selectList(ssQueryWrapper);
        if(CollectionUtils.isNotEmpty(sceniczoneScenicspots)) {
            for (SceniczoneScenicspot sceniczoneScenicspot : sceniczoneScenicspots) {
                scenicSpots.add(scenicspotMapper.selectById(sceniczoneScenicspot.getScenicSpot_id()));
            }
        }
        return scenicSpots;
    }

    //通过资源id查景点id
    @Override
    public Long getScenicspotIdByResourceId(Long resourceId) {
        ScenicspotResource scenicspotResource = scenicspotResourceMapper.selectOne(new QueryWrapper<ScenicspotResource>().eq("resource_id", resourceId).eq("scenicSpot_resource_use", true));

        return scenicspotResource.getScenicSpot_id();
    }

    //通过景点id查景区id
    @Override
    public Long getSceniczoneIdByScenicspotId(Long scenicspot_id) {
        SceniczoneScenicspot sceniczoneScenicspot = sceniczoneScenicspotMapper.selectOne(new QueryWrapper<SceniczoneScenicspot>().eq("scenicSpot_id", scenicspot_id).eq("scenicZone_scenicSpot_use", true));
        return sceniczoneScenicspot.getScenicZone_id();
    }



}
