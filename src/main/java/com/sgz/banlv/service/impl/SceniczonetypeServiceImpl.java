package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.entity.Sceniczonetype;
import com.sgz.banlv.mapper.SceniczoneMapper;
import com.sgz.banlv.mapper.SceniczonetypeMapper;
import com.sgz.banlv.service.ISceniczonetypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgz.banlv.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 景区分类表 服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class SceniczonetypeServiceImpl extends ServiceImpl<SceniczonetypeMapper, Sceniczonetype> implements ISceniczonetypeService {

    @Resource
    private SceniczoneMapper sceniczoneMapper;

    @Override
    public Result<List<Sceniczone>> GetScenicZoneByScenicZoneTypeId(Integer city_id, Integer scenicZoneType_id, Integer currentPage) {
        List<Sceniczone> sceniczoneList = new ArrayList<>();
        QueryWrapper<Sceniczone> szQuery = new QueryWrapper<Sceniczone>()
                .eq("city_id", city_id)
                .eq("scenicZoneType_id", scenicZoneType_id);
        Long szNum = sceniczoneMapper.selectCount(szQuery);
        if (szNum >= (currentPage - 1 )* 10) {
            IPage<Sceniczone> page = new Page<>(currentPage, 10);
            IPage<Sceniczone> szPage = sceniczoneMapper.selectPage(page, szQuery);
            if(CollectionUtils.isNotEmpty(szPage.getRecords())) {
                sceniczoneList.addAll(szPage.getRecords());
            }

//            PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage, 10, scenicZone);
//            scenicZoneList.addAll(scenicZonePageBean.getList());

        }else {
            IPage<Sceniczone> page = new Page<>(currentPage - szNum % 10, 10);
            IPage<Sceniczone> szPage = sceniczoneMapper.selectPage(page, new QueryWrapper<Sceniczone>()
                    .eq("scenicZoneType_id", scenicZoneType_id));
            if(CollectionUtils.isNotEmpty(szPage.getRecords())) {
                sceniczoneList.addAll(szPage.getRecords());
            }
//            scenicZone.setCity_id(0);
//            PageBean<ScenicZone> scenicZonePageBean = scenicZoneService.searchAllByPage(currentPage - szNum % 10, 10, scenicZone);
//            scenicZoneList.addAll(scenicZonePageBean.getList());
        }
        return !sceniczoneList.isEmpty() ? Result.success(sceniczoneList) : Result.failure();
    }


}
