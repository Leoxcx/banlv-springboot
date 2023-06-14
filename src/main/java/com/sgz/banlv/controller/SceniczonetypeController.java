package com.sgz.banlv.controller;

import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.service.ISceniczonetypeService;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 景区分类表 前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
public class SceniczonetypeController {

    @Resource
    private ISceniczonetypeService sceniczonetypeService;

    //通过当前城市city_id和景区分类scenicZoneType_id（自然风景区、展馆、博物馆、公园）获取当前城市景区列表
    @RequestMapping("/getsceniczonebysceniczonetypeid")
    public Result<List<Sceniczone>> GetScenicZoneByScenicZoneTypeId(@RequestParam Integer city_id ,@RequestParam Integer scenicZoneType_id, @RequestParam Integer currentPage) {
        if(CommonUtils.isIntegerNotEmpty(city_id) && CommonUtils.isIntegerNotEmpty(scenicZoneType_id) && CommonUtils.isIntegerNotEmpty(currentPage)) {
            return sceniczonetypeService.GetScenicZoneByScenicZoneTypeId(city_id, scenicZoneType_id, currentPage);
        }
        return Result.failure();
    }
}
