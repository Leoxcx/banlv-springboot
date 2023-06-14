package com.sgz.banlv.controller;

import com.sgz.banlv.entity.ScenicspotResource;
import com.sgz.banlv.service.IScenicspotResourceService;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class ScenicspotResourceController {

    @Resource
    private IScenicspotResourceService scenicspotResourceService;


    @RequestMapping("/scenicSpot_resourcetotalsearchservlet")
    public Result<List<ScenicspotResource>> GetScenicResourceInfo(@RequestParam Long scenicSpot_id) {
        if(CommonUtils.isLongNotEmpty(scenicSpot_id)) {
            return scenicspotResourceService.GetScenicResourceInfo(scenicSpot_id);
        }else {
            return Result.failure();
        }
    }

}
