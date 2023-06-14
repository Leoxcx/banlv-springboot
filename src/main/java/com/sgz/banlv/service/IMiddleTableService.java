package com.sgz.banlv.service;

import com.sgz.banlv.entity.*;

import java.util.List;

public interface IMiddleTableService {

    List<ScenicResource> spotToResourceLimit(long spotId);

    //    景点id找当前景点的所有资源resource
    List<ScenicResource> spotToResource(long spotId);

    Scenicspot resourceToScenicSpot(long resource_id);

    //    通过scenicSpotId在景区景点中间表中获取scenicZone信息
    Sceniczone scenicSpotToScenicZone(long scenicSpot_id);


    //    通过景区id找到所有景点
    List<Scenicspot> scenicZoneSpotToScenic(long scenicZoneId);

    //通过资源id查景点id
    Long getScenicspotIdByResourceId(Long resourceId);

    //通过景点id查景区id
    Long getSceniczoneIdByScenicspotId(Long scenicspot_id);
}

