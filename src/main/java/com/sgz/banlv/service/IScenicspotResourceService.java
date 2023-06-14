package com.sgz.banlv.service;

import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.entity.ScenicspotResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.vo.Result;

import java.awt.*;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IScenicspotResourceService extends IService<ScenicspotResource> {


    Result<List<ScenicspotResource>> GetScenicResourceInfo(Long scenicSpot_id);
}
