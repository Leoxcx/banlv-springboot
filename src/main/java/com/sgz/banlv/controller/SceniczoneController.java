package com.sgz.banlv.controller;

import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.dto.ScenicSpotInfoDto;
import com.sgz.banlv.service.ISceniczoneService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 景区 前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
public class SceniczoneController {
    @Resource
    private ISceniczoneService sceniczoneService;

    //获取景区播放记录
    @RequestMapping("/getsceniczoneplayrecord")
    public Result<Integer> GetScenicZonePlayRecord(@RequestParam Long resource_id) {
        if(CommonUtils.isLongNotEmpty(resource_id)) {
            return sceniczoneService.GetScenicZonePlayRecord(resource_id);
        }else {
            return Result.failure();
        }
    }

    //查看当前景区内所有景点信息，以及景点内的资源
    @RequestMapping("getscenicspotinfobyzoneid")
    public Result<List<ScenicSpotInfoDto>> GetScenicSpotInfoByZoneId(@RequestParam Long scenicZone_id) throws IllegalAccessException {
        if(CommonUtils.isLongNotEmpty(scenicZone_id)) {
            return sceniczoneService.GetScenicSpotInfoByZoneId(scenicZone_id);
        }else {
            return Result.failure();
        }
    }

    //获取热门景区信息
    @RequestMapping("/getpopularsceniczone")
    public Result<List<Sceniczone>> GetPopularScenicZone(@RequestParam Integer city_id, @RequestParam Integer currentPage) {
        if(CommonUtils.isIntegerNotEmpty(city_id) && CommonUtils.isIntegerNotEmpty(currentPage)) {
            return sceniczoneService.GetPopularScenicZone(city_id, currentPage);
        }
        return Result.failure();
    }

    //通过传入的city_id和scenicZone_name模糊查询获取景区信息
    @RequestMapping("/fuzzyquerysceniczoneinfo")
    public Result<List<Sceniczone>> FuzzyQueryCityScenicZoneInfo(@RequestParam String scenicZone_name,@RequestParam Integer city_id) {
        if(CommonUtils.isIntegerNotEmpty(city_id) && StringUtils.isNotEmpty(scenicZone_name)) {
            return sceniczoneService.FuzzyQueryCityScenicZoneInfo(scenicZone_name, city_id);
        }
        return Result.failure();
    }

    //通过景区id查询景区信息信息
    @RequestMapping("/scenicZonetotalsearchservlet")
    public Result<Sceniczone> SearchTotalInfo(@RequestParam Long scenicZone_id) {
        if(CommonUtils.isLongNotEmpty(scenicZone_id)) {
            return sceniczoneService.SearchTotalInfo(scenicZone_id);
        }else {
            return Result.failure();
        }
    }

}
