package com.sgz.banlv.service;

import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.entity.Sceniczonetype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.vo.Result;

import java.util.List;

/**
 * <p>
 * 景区分类表 服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface ISceniczonetypeService extends IService<Sceniczonetype> {

    Result<List<Sceniczone>> GetScenicZoneByScenicZoneTypeId(Integer city_id, Integer scenicZoneType_id, Integer currenPage);
}
