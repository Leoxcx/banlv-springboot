package com.sgz.banlv.controller;

import com.sgz.banlv.entity.Sceniczoneentry;
import com.sgz.banlv.mapper.SceniczoneSceniczoneentryMapper;
import com.sgz.banlv.service.ISceniczoneSceniczoneentryService;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 景区词条中间表 前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@RestController
@RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
public class SceniczoneSceniczoneentryController {

    @Resource
    private ISceniczoneSceniczoneentryService ssEntryService;


//    通过景区Id获取所有的景区词条
    @RequestMapping("/getsceniczoneentriesbysceniczoneid")
    public Result<List<Sceniczoneentry>> GetScenicZoneEntriesByScenicZoneId(@RequestParam Long scenicZone_id) {
        if(CommonUtils.isLongNotEmpty(scenicZone_id)) {
            return ssEntryService.GetScenicZoneEntriesByScenicZoneId(scenicZone_id);
        }
        return Result.failure();
    }
}
