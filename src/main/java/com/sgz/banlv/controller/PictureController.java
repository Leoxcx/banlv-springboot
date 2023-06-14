package com.sgz.banlv.controller;

import com.sgz.banlv.entity.City;
import com.sgz.banlv.entity.Picture;
import com.sgz.banlv.service.ICityService;
import com.sgz.banlv.service.IPictureService;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 图片资源表 前端控制器
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@RestController
@RequestMapping( method = {RequestMethod.GET,RequestMethod.POST})
public class PictureController {

    @Resource
    private IPictureService pictureService;
    //通过picture_id查picture信息
    @RequestMapping("/picturetotalsearchservlet")
    public Result<Picture> GetPictureInfo(@RequestParam Long picture_id) {
        if(CommonUtils.isLongNotEmpty(picture_id)) {
            return pictureService.GetPictureInfo(picture_id);
        }
        return Result.failure();
    }

}
