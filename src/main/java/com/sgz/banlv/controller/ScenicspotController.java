package com.sgz.banlv.controller;

import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;

import com.sgz.banlv.entity.*;
import com.sgz.banlv.service.IScenicspotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
public class ScenicspotController {
    @Resource
    private IScenicspotService scenicspotService;

    //景点scenicSpot_id获取资源（前十个）
    @RequestMapping("/spotrecommendservlet")
    public Result<List<ScenicResource>> ScenicSpotRecommendServlet(@RequestParam Long scenicSpot_id) {
        if(scenicSpot_id != null && scenicSpot_id != 0l){
            return scenicspotService.ScenicSpotRecommend(scenicSpot_id);
        }else {
            return Result.failure();
        }
    }

    //获取景点播放记录
    @RequestMapping("/getscenicspotplayrecord")
    public Result<Integer> GetScenicSpotPlayRecord(@RequestParam Long resource_id) {
        if(CommonUtils.isLongNotEmpty(resource_id)) {
            return scenicspotService.GetScenicSpotPlayRecord(resource_id);
        }else {
            return Result.failure();
        }
    }

    //通过景点id获取对应景点资源列表
    @RequestMapping("/getresourcesbyscenicspotid")
    public Result<List<ScenicResource>> GetResourcesByScenicSpotId(@RequestParam Long scenicSpot_id) {
        if(CommonUtils.isLongNotEmpty(scenicSpot_id)) {
            return scenicspotService.GetResourcesByScenicSpotId(scenicSpot_id);
        }else {
            return Result.failure();
        }
    }

    //通过景点id获取景点信息
    @RequestMapping("/scenicSpottotalsearchservlet")
    public Result<Scenicspot> SearchTotalInfo(@RequestParam Long scenicSpot_id) {
        if(CommonUtils.isLongNotEmpty(scenicSpot_id)) {
            return scenicspotService.SearchTotalInfo(scenicSpot_id);
        }else {
            return Result.failure();
        }
    }


    //通过景点id获取当前景区所有的景点信息
    @RequestMapping("/getallscenicspotbyscenicspotid")
    public Result<List<Scenicspot>> GetAllScenicSpotByScenicSpotId(@RequestParam Long scenicSpot_id) {
        if(CommonUtils.isLongNotEmpty(scenicSpot_id)) {
            return scenicspotService.GetAllScenicSpotByScenicSpotId(scenicSpot_id);
        }else {
            return Result.failure();
        }
    }




}
