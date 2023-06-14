package com.sgz.banlv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgz.banlv.common.model.PlayNum;
import com.sgz.banlv.utils.CommonUtils;
import com.sgz.banlv.vo.Result;
import com.sgz.banlv.dto.ScenicSpotInfoDto;
import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.mapper.SceniczoneMapper;
import com.sgz.banlv.service.IMiddleTableService;
import com.sgz.banlv.service.ISceniczoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 景区 服务实现类
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Slf4j
@Service
public class SceniczoneServiceImpl extends ServiceImpl<SceniczoneMapper, Sceniczone> implements ISceniczoneService {

    @Resource
    private IMiddleTableService middleTableService;
    @Resource
    private SceniczoneMapper sceniczoneMapper;

//    获取景区播放记录
    @Override
    public Result<Integer> GetScenicZonePlayRecord(Long resource_id) {

        Long scenicSpot_id = middleTableService.getScenicspotIdByResourceId(resource_id);
        if(scenicSpot_id != null) {
            Long scenicZone_id = middleTableService.getSceniczoneIdByScenicspotId(scenicSpot_id);
            if(scenicZone_id != null) {
                //获取资源对应景区播放记录
                int scenicZoneRecord = PlayNum.getPlayNumModel().getScenicZoneRecord(scenicZone_id);

                if(scenicZoneRecord != 0) {
                    return Result.success(scenicZoneRecord);
                }
            }
        }
        return Result.failure();
    }

    //查看当前景区内所有景点信息，以及景点内的资源
    @Override
    public Result<List<ScenicSpotInfoDto>> GetScenicSpotInfoByZoneId(Long scenicZone_id) throws IllegalAccessException {
        List<ScenicSpotInfoDto> scenicSpotInfoDtos = new ArrayList<>();
        List<Scenicspot> scenicspots = middleTableService.scenicZoneSpotToScenic(scenicZone_id);
        if(CollectionUtils.isNotEmpty(scenicspots)) {
            for (Scenicspot scenicspot : scenicspots) {
                List<ScenicResource> scenicResources = middleTableService.spotToResource(scenicspot.getScenicSpot_id());
                ScenicSpotInfoDto scenicSpotInfoDto = new ScenicSpotInfoDto();

                CommonUtils.copy(scenicspot,scenicSpotInfoDto);


//                scenicSpotInfoDto.setScenicSpot_id(scenicspot.getScenicSpot_id());
//                scenicSpotInfoDto.setScenicSpot_name(scenicspot.getScenicSpot_name());
//                scenicSpotInfoDto.setScenicSpot_latitude(scenicspot.getScenicSpot_latitude());
//                scenicSpotInfoDto.setScenicSpot_longitude(scenicspot.getScenicSpot_longitude());
//                scenicSpotInfoDto.setScenicSpot_range(scenicspot.getScenicSpot_range());
//                scenicSpotInfoDto.setScenicSpot_num(scenicspot.getScenicSpot_num());

                scenicSpotInfoDto.setResources(scenicResources);

                scenicSpotInfoDtos.add(scenicSpotInfoDto);
            }
        }
        if(CollectionUtils.isNotEmpty(scenicSpotInfoDtos)) {
            return Result.success(scenicSpotInfoDtos);
        }
        return Result.failure();
    }

//    获取热门景区信息
    @Override
    public Result<List<Sceniczone>> GetPopularScenicZone(Integer city_id, Integer currentPage) {
        QueryWrapper<Sceniczone> szQueryWrapper = new QueryWrapper<>();
        szQueryWrapper.eq("city_id", city_id);

        IPage<Sceniczone> sceniczonePage = new Page<>(currentPage,10);

        IPage<Sceniczone> sceniczoneIPage = sceniczoneMapper.selectPage(sceniczonePage, szQueryWrapper);
        List<Sceniczone> records = sceniczoneIPage.getRecords();
        if(CollectionUtils.isNotEmpty(records)) {
            return Result.success(records);
        }
        return Result.failure();
    }

    //通过传入的city_id和scenicZone_name模糊查询获取景区信息
    @Override
    public Result<List<Sceniczone>> FuzzyQueryCityScenicZoneInfo(String scenicZone_name, Integer city_id) {
        List<Sceniczone> sceniczoneList = new ArrayList<>();
//        QueryWrapper<Sceniczone> queryWrapper = new QueryWrapper<>();
        //通过传入的city_id和scenicZone_name查询获取景区信息
        Sceniczone sceniczone = sceniczoneMapper.selectOne(new QueryWrapper<Sceniczone>().eq("city_id", city_id)
                .eq("scenicZone_name", scenicZone_name)
                .eq("scenicZone_use", true)
                .last("LIMIT 1;"));
        Long count = sceniczoneMapper.selectCount(new QueryWrapper<Sceniczone>().eq("scenicZone_name", scenicZone_name).eq("scenicZone_use", true));
        //查到则放入list中
        if(sceniczone != null) {
            sceniczoneList.add(sceniczone);
            if(count >= 9) {
                fetchScenicZoneInfo(sceniczoneList, city_id, scenicZone_name);
                return Result.success(sceniczoneList);
            }
        }
        fetchScenicZoneInfo(sceniczoneList, city_id, scenicZone_name);
        IPage<Sceniczone> sceniczonePage = new Page<>(1,10 - sceniczoneList.size());
        QueryWrapper<Sceniczone> szQueryWrapper = new QueryWrapper<>();
        szQueryWrapper.eq("city_id", city_id)
                .eq("scenicZone_use", true);
        log.info("--------------------------------");
        IPage<Sceniczone> sceniczoneIPage = sceniczoneMapper.selectPage(sceniczonePage, szQueryWrapper);
        sceniczoneList.addAll(sceniczoneIPage.getRecords());
        if(sceniczoneList.size() < 10){
//            IPage<Sceniczone> sceniczonePage = new Page<>(1,10 - sceniczoneList.size());
        IPage<Sceniczone> szPage = sceniczoneMapper.selectPage(sceniczonePage, null);
        sceniczoneList.addAll(szPage.getRecords());
        }
        return Result.success(sceniczoneList);


//        fetchScenicZoneInfo(sceniczoneList,scenicZone_name);
//        IPage<Sceniczone> sceniczonePage = new Page<>(1,10 - sceniczoneList.size());
//        IPage<Sceniczone> sceniczoneIPage = sceniczoneMapper.selectPage(sceniczonePage, null);
//        sceniczoneList.addAll(sceniczoneIPage.getRecords());
//        return Result.success(sceniczoneList);




//        queryWrapper.eq("city_id", city_id)
//                        .eq("scenicZone_name", scenicZone_name)
//                        .last("LIMIT 1");
//        Sceniczone sceniczone = sceniczoneMapper.selectOne(queryWrapper);
//        if(sceniczone == null) {
//            QueryWrapper<Sceniczone> listQueryWrapper = new QueryWrapper<>();
//            listQueryWrapper.eq("city_id", city_id)
//                    .like("scenicZone_name", scenicZone_name);
//            sceniczoneList = sceniczoneMapper.selectList(listQueryWrapper);
//            if(CollectionUtils.isNotEmpty(sceniczoneList) && sceniczoneList.size() == 10) {
//                return Result.success(sceniczoneList);
//            }
//        }
//        sceniczoneList.add(sceniczone);
//        return null;
    }

    //通过景区id查询景区信息信息
    @Override
    public Result<Sceniczone> SearchTotalInfo(Long scenicZone_id) {
        Sceniczone sceniczone = this.getById(scenicZone_id);
        return Optional.ofNullable(sceniczone)
                .map(Result::success)
                .orElse(Result.failure());
    }


    private List<Sceniczone> fetchScenicZoneInfo(List<Sceniczone> sceniczoneList, Integer city_id,String scenicZone_name) {
        List<Sceniczone> multipleList = sceniczoneMapper.selectList(new QueryWrapper<Sceniczone>().eq("city_id", city_id).like("scenicZone_name", scenicZone_name).eq("scenicZone_use", true).last("LIMIT 1, 10"));
        sceniczoneList.addAll(multipleList);
        return sceniczoneList;
    }


}
