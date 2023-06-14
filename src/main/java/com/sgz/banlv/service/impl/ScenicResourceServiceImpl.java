package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgz.banlv.common.model.PlayNum;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.*;
import com.sgz.banlv.mapper.ScenicResourceMapper;
import com.sgz.banlv.mapper.UserMapper;
import com.sgz.banlv.mapper.UserResourceMapper;
import com.sgz.banlv.service.IMiddleTableService;
import com.sgz.banlv.service.IScenicResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class ScenicResourceServiceImpl extends ServiceImpl<ScenicResourceMapper, ScenicResource> implements IScenicResourceService {


    @Resource
    private ScenicResourceMapper resourceMapper;
    @Resource
    private IMiddleTableService middleTableService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserResourceMapper userResourceMapper;
    //    通过资源主键 获取对应景点信息
    @Override
    public Result<Scenicspot> ResourceToScenicSpotServlet(Long resource_id) {

        Scenicspot scenicspot = middleTableService.resourceToScenicSpot(resource_id);


        if(scenicspot != null) {
            return Result.success(scenicspot);
        }
        return Result.failure();
    }

    //通过resource_id和用户的openid返回用户是否有权限播放
    @Override
    public Result<Boolean> IsAbleToPlay(Long resource_id, String user_openid) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_openid", user_openid)
                .last("LIMIT 1");
        User user = userMapper.selectOne(userQueryWrapper);

        QueryWrapper<UserResource> userResourceQueryWrapper = new QueryWrapper<>();
        userResourceQueryWrapper.eq("user_id", user.getUser_id())
                                .eq("resource_id", resource_id);
        UserResource userResource = userResourceMapper.selectOne(userResourceQueryWrapper);
        if(userResource != null) {
            return Result.success();
        }
        return Result.failure();
    }

    //通过resource_id和用户的id返回用户是否有权限播放
    @Override
    public Result<Boolean> IsAbleToPlayById(Long resource_id, Long user_id) {
        QueryWrapper<UserResource> userResourceQueryWrapper = new QueryWrapper<>();
        userResourceQueryWrapper.eq("user_id", user_id)
                .eq("resource_id", resource_id);
        UserResource userResource = userResourceMapper.selectOne(userResourceQueryWrapper);
        if(userResource != null) {
            return Result.success();
        }
        return Result.failure();
    }
    //存播放记录
    @Override
    public Result<Boolean> SavePlayRecord(Long resource_id) {
        //存资源播放记录
        PlayNum.getPlayNumModel().resourcesRecord(resource_id);

        //通过景点资源中间表，获取scenicSpotId景点id
        Scenicspot scenicspot = middleTableService.resourceToScenicSpot(resource_id);
        if(scenicspot == null) {
            return Result.failure();
        }
        //存资源对应景点播放记录
        PlayNum.getPlayNumModel().scenicSpotRecord(scenicspot.getScenicSpot_id());

        //通过景区景点中间表，scenicZoneId景区id
        Sceniczone sceniczone = middleTableService.scenicSpotToScenicZone(scenicspot.getScenicSpot_id());
        if(sceniczone == null) {
            return Result.failure();
        }
        //存资源对应景区播放记录
        PlayNum.getPlayNumModel().scenicZoneRecord(sceniczone.getScenicZone_id());

        return Result.success();
    }

    //获取资源播放记录
    @Override
    public Result<Integer> GetResourcePlayRecord(Long resource_id) {
        //获取资源播放记录
        int resourceRecord = PlayNum.getPlayNumModel().getResourceRecord(resource_id);

        if(resourceRecord != 0) {
            return Result.success(resourceRecord);
        }

        return Result.failure();
    }

    //    通过资源id查一条资源信息
    @Override
    public Result<ScenicResource> GetScenicResourceInfo(Long resource_id) {
        ScenicResource scenicResource = resourceMapper.selectOne(new QueryWrapper<ScenicResource>()
                .eq("resource_id", resource_id)
                .last("LIMIT 1"));
        if(scenicResource != null) {
            return Result.success(scenicResource);
        }
        return Result.failure();
    }



}
