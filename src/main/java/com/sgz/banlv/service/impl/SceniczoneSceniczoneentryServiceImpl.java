package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.sgz.banlv.entity.SceniczoneSceniczoneentry;
import com.sgz.banlv.entity.Sceniczoneentry;
import com.sgz.banlv.mapper.SceniczoneSceniczoneentryMapper;
import com.sgz.banlv.mapper.SceniczoneentryMapper;
import com.sgz.banlv.service.ISceniczoneSceniczoneentryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 景区词条中间表 服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class SceniczoneSceniczoneentryServiceImpl extends ServiceImpl<SceniczoneSceniczoneentryMapper, SceniczoneSceniczoneentry> implements ISceniczoneSceniczoneentryService {

    @Resource
    private SceniczoneSceniczoneentryMapper ssEntryMapper;

    @Resource
    private SceniczoneentryMapper szEntryMapper;

    //通过景区词条中间表的景区id查询当前景区的所有词条
    @Override
    public Result<List<Sceniczoneentry>> GetScenicZoneEntriesByScenicZoneId(Long scenicZone_id) {
        List<SceniczoneSceniczoneentry> sceniczoneSceniczoneentries = ssEntryMapper.selectList(new QueryWrapper<SceniczoneSceniczoneentry>().eq("scenicZone_id", scenicZone_id).eq("scenicZone_scenicSpot_use", true));

        List<Sceniczoneentry> sceniczoneEntryList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(sceniczoneSceniczoneentries)) {
            for (SceniczoneSceniczoneentry ssEentry : sceniczoneSceniczoneentries) {
                Sceniczoneentry sceniczoneentry = szEntryMapper.selectById(ssEentry.getScenicZoneEntry_id());
                if(sceniczoneentry != null) {
                    sceniczoneEntryList.add(sceniczoneentry);
                }

            }
        }
        return !sceniczoneEntryList.isEmpty() ? Result.success(sceniczoneEntryList) : Result.failure();
    }
}
