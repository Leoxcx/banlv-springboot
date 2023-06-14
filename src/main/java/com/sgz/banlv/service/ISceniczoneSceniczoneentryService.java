package com.sgz.banlv.service;

import com.sgz.banlv.entity.SceniczoneSceniczoneentry;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sgz.banlv.entity.Sceniczoneentry;
import com.sgz.banlv.vo.Result;

import java.util.List;

/**
 * <p>
 * 景区词条中间表 服务类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
public interface ISceniczoneSceniczoneentryService extends IService<SceniczoneSceniczoneentry> {

    Result<List<Sceniczoneentry>> GetScenicZoneEntriesByScenicZoneId(Long scenicZone_id);
}
