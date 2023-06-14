package com.sgz.banlv.service;

import com.sgz.banlv.entity.City;
import com.sgz.banlv.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.vo.Result;

/**
 * <p>
 * 图片资源表 服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface IPictureService extends IService<Picture> {


    Result<Picture> GetPictureInfo(Long picture_id);
}
