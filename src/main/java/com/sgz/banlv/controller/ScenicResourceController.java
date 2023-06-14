package com.sgz.banlv.controller;

import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.service.IScenicResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
public class ScenicResourceController {

    @Resource
    private IScenicResourceService scenicResourceService;

    //资源resource_id获取景点信息
    @RequestMapping("/resourcetoscenicspotservlet")
    public Result<Scenicspot> ResourceToScenicSpotServlet(@RequestParam Long resource_id){
        if(resource_id != null && resource_id != 0l){
            return scenicResourceService.ResourceToScenicSpotServlet(resource_id);
        }else {
            return Result.failure();
        }
    }

    //通过resource_id和用户的openid返回用户是否有权限播放
    @RequestMapping("/isabletoplay")
    public Result<Boolean> IsAbleToPlay(@RequestParam Long resource_id, @RequestParam String user_openid){
        if(resource_id != null && resource_id != 0l && !StringUtils.isEmpty(user_openid)){
            return scenicResourceService.IsAbleToPlay(resource_id,user_openid);
        }else {
            return Result.failure();
        }
    }

    //通过resource_id和用户的id返回用户是否有权限播放
    @RequestMapping("/isabletoplaybyid")
    public Result<Boolean> IsAbleToPlayById(@RequestParam Long resource_id, @RequestParam Long user_id){
        if(resource_id != null && resource_id != 0l && user_id != null && user_id != 0l){
            return scenicResourceService.IsAbleToPlayById(resource_id,user_id);
        }else {
            return Result.failure();
        }
    }

    //传入resource_id传入景点id将对应景点的播放数+1对应的景区的播放数+1
    @RequestMapping("/saveplayrecord")
    public Result<Boolean> SavePlayRecord(@RequestParam Long resource_id){
        if(resource_id != null && resource_id != 0l ){
            return scenicResourceService.SavePlayRecord(resource_id);
        }else {
            return Result.failure();
        }
    }

    //获取资源播放记录
    @RequestMapping("/getresourceplayrecord")
    public Result<Integer> GetResourcePlayRecord(@RequestParam Long resource_id) {
        if(resource_id != 0 && resource_id != null) {
            return scenicResourceService.GetResourcePlayRecord(resource_id);
        }else {
            return Result.failure();
        }
    }

    @RequestMapping("/resourcesearchservlet")
    public Result<ScenicResource> GetScenicResourceInfo(@RequestParam Long resource_id) {
        if(CommonUtils.isLongNotEmpty(resource_id)) {
            return scenicResourceService.GetScenicResourceInfo(resource_id);
        }else {
            return Result.failure();
        }
    }
}
