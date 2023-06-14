package com.sgz.banlv.common.model;

import com.sgz.banlv.entity.ScenicResource;
import com.sgz.banlv.entity.Scenicspot;
import com.sgz.banlv.entity.Sceniczone;
import com.sgz.banlv.mapper.ScenicResourceMapper;
import com.sgz.banlv.mapper.ScenicspotMapper;
import com.sgz.banlv.mapper.SceniczoneMapper;
import com.sgz.banlv.service.impl.ScenicResourceServiceImpl;
import com.sgz.banlv.service.impl.ScenicspotServiceImpl;
import com.sgz.banlv.service.impl.SceniczoneServiceImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//播放数记录
@Component
@Order(4)
public class PlayNum {

    public static Map<Long, Integer> resourceMap;
    public static Map<Long, Integer> scenicSpotMap;
    public static Map<Long, Integer> scenicZoneMap;

    private ScenicResourceMapper scenicResourceMapper;
    private ScenicspotMapper scenicspotMapper;
    private SceniczoneMapper sceniczoneMapper;
    // 单例
    // 空参构造
    private PlayNum() {}
    private static PlayNum playNumModel = null;
    //静态工厂方法
    public static PlayNum getPlayNumModel() {
        if (playNumModel == null) {
            playNumModel = new PlayNum();

            init();
        }
        return playNumModel;
    }

    public PlayNum setResourceMapper(ScenicResourceMapper scenicResourceMapper) {
        this.scenicResourceMapper = scenicResourceMapper;
        return this;
    }

    public PlayNum setScenicspotMapper(ScenicspotMapper scenicspotMapper) {
        this.scenicspotMapper = scenicspotMapper;
        return this;
    }

    public PlayNum setSceniczoneMapper(SceniczoneMapper sceniczoneMapper) {
        this.sceniczoneMapper = sceniczoneMapper;
        return this;
    }

    // 重新初始化单例的方法
    public static void reinitialize() {
        init();
    }

    private static void init(){
        //初始化存资源播放记录hashmap
        resourceMap = new HashMap<>();

        //初始化存资源对应景点播放记录hashmap
        scenicSpotMap = new HashMap<>();

        //初始化存资源对应景区播放记录hashmap
        scenicZoneMap = new HashMap<>();
    }

    //存资源播放记录
    public void resourcesRecord(long resource_id) {
        updateRecord(resourceMap,resource_id);
    }

    //存资源对应景点播放记录
    public void scenicSpotRecord(long scenicSpot_id) {
        updateRecord(scenicSpotMap,scenicSpot_id);
    }

    //存资源对应景区播放记录
    public void scenicZoneRecord(long scenicZone_id) {
        updateRecord(scenicZoneMap,scenicZone_id);
    }

    private void updateRecord(Map<Long, Integer> map,long id) {
        int num = 1;//初始化播放次数
        //判断是否存有记录
        if(map.containsKey(id)) {
            num = map.get(id);
            num++;
        }
        // 添加键值对
        map.put(id, num);

    }

    public int getResourceRecord(long resource_id) {
        if(resourceMap.get(resource_id) != null){
            return resourceMap.get(resource_id);
        }
        return 0;
    }

    public int getScenicSpotRecord(long scenicSpot_id) {
        if(scenicSpotMap.get(scenicSpot_id) != null){
            return scenicSpotMap.get(scenicSpot_id);
        }
        return 0;
    }

    public int getScenicZoneRecord(long scenicZone_id) {
        if(scenicZoneMap.get(scenicZone_id) != null){
            return scenicZoneMap.get(scenicZone_id);
        }
        return 0;
    }

    public boolean saveResourceRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : resourceMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);


            //通过ID查对应资源表，将资源数据中的num累加再更新入库
            ScenicResource scenicResource = scenicResourceMapper.selectById(key);

            int rsNum = scenicResource.getResource_num() + value;
            scenicResource.setResource_num(rsNum);
            System.out.println(scenicResource.toString());
            int i = scenicResourceMapper.updateById(scenicResource);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean saveScenicSpotRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : scenicSpotMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);

            //获取数据库中的播放记录，于当前记录相加
            Scenicspot scenicspot = scenicspotMapper.selectById(key);
            int ssNum = scenicspot.getScenicSpot_num() + value;
            scenicspot.setScenicSpot_num(ssNum);
            System.out.println(scenicspot.toString());
//            scenicSpot.setScenicSpot_num(value);
            int i = scenicspotMapper.updateById(scenicspot);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }


    public boolean saveScenicZoneRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : scenicZoneMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);

            Sceniczone sceniczone = sceniczoneMapper.selectById(key);
            int ssNum = sceniczone.getScenicZone_number() + value;
            sceniczone.setScenicZone_number(ssNum);
            System.out.println(sceniczone.toString());
//            scenicZone.setScenicZone_number(value);
            int i = sceniczoneMapper.updateById(sceniczone);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }

}
