package com.sgz.banlv.common.model;

import com.sgz.banlv.dto.CoordinateRecordDto;
import com.sgz.banlv.dto.ScenicspotDto;

import java.util.*;

//代理商暂存景点信息

public class CoordinateRecordInfo {

    public static Map<Long, List<CoordinateRecordDto>> coordinateRecordMap;



    // 单例
    // 空参构造
    private CoordinateRecordInfo() {}
    private static CoordinateRecordInfo coordinateRecordModel = null;
    //静态工厂方法
    public static CoordinateRecordInfo getCoordinateRecordModel() {
        if (coordinateRecordModel == null) {
            coordinateRecordModel = new CoordinateRecordInfo();

            init();
        }
        return coordinateRecordModel;
    }

    // 重新初始化单例的方法
    public static void reinitialize() {
        if (coordinateRecordModel != null) {
            // 执行销毁或重置操作，例如将实例设置为 null
            // ...
            coordinateRecordModel = null;
        }
        // 执行初始化操作
        coordinateRecordModel = new CoordinateRecordInfo();
    }

    private static void init(){
        //代理商暂存景点信息
        coordinateRecordMap = new HashMap<>();

    }

    //存代理商暂存景点信息
    public void CoordinateRecord(CoordinateRecordDto updateRecordVo) {updateRecord(updateRecordVo);}


    private void updateRecord(CoordinateRecordDto updateRecordVo) {

        long id = updateRecordVo.getAgent_id();
        //判断是否存有记录
        if(coordinateRecordMap.containsKey(id)) {
            List<CoordinateRecordDto> localRecordVos = coordinateRecordMap.get(id);
            Iterator<CoordinateRecordDto> iterator = localRecordVos.iterator();
            while (iterator.hasNext()) {
                CoordinateRecordDto localRecordVo = iterator.next();
                //判断传入的vo城市id和景区名是否存在于map中
                if(localRecordVo.getCity_id().equals(updateRecordVo.getCity_id()) && localRecordVo.getScenicZone_name().equals(updateRecordVo.getScenicZone_name())) {
                    //存在则将ScenicspotVo列表添加到对应CoordinateRecordVo的ScenicspotVo列表末尾
                    localRecordVo.getScenicspotDto().addAll(updateRecordVo.getScenicspotDto());
                }else {
                    //若不存在则将整个传入的vo存入map中的CoordinateRecordVo列表中
                    localRecordVos.add(updateRecordVo);
                }

            }

//            for (CoordinateRecordDto localRecordVo : localRecordVos) {
//                //判断传入的vo城市id和景区名是否存在于map中
//                if(localRecordVo.getCity_id() == updateRecordVo.getCity_id() && localRecordVo.getScenicZone_name() == updateRecordVo.getScenicZone_name()) {
//                    //存在则将ScenicspotVo列表添加到对应CoordinateRecordVo的ScenicspotVo列表末尾
//                    localRecordVo.getScenicspotDto().addAll(updateRecordVo.getScenicspotDto());
//                }else {
//                    //若不存在则将整个传入的vo存入map中的CoordinateRecordVo列表中
//                    localRecordVos.add(updateRecordVo);
//                }
//            }

        }else {
            List<CoordinateRecordDto> crList = new ArrayList<CoordinateRecordDto>();
            crList.add(updateRecordVo);
            coordinateRecordMap.put(id,crList);
        }

    }

//获取代理商暂存景点信息
    public List<CoordinateRecordDto> getCoordinateRecord(long agent_id) {
        if(coordinateRecordMap.containsKey(agent_id)){
            return coordinateRecordMap.get(agent_id);
        }
        return null;
    }

    //    删除定位记录
    public boolean removeRecord(long agentId, String uuid) {
        if(coordinateRecordMap.containsKey(agentId)) {
            List<CoordinateRecordDto> coordinateRecordDtos = coordinateRecordMap.get(agentId);
            Iterator<CoordinateRecordDto> iterator = coordinateRecordDtos.iterator();
            while(iterator.hasNext()) {
                CoordinateRecordDto coordinateRecordDto = iterator.next();
                List<ScenicspotDto> scenicspotDtos = coordinateRecordDto.getScenicspotDto();
                Iterator<ScenicspotDto> scenicspotVoIterator = scenicspotDtos.iterator();
                while(scenicspotVoIterator.hasNext()){
                    ScenicspotDto scenicspotDto = scenicspotVoIterator.next();
                    if (scenicspotDto.getUuid().equals(uuid)) {
                        scenicspotVoIterator.remove();
                        return true;
                    }
                }
            }
        }
        return false;
    }


//
//    public boolean saveResourceRecordToSql(){
//        // 使用迭代器遍历 HashMap
//        for (Map.Entry<Long, Integer> entry : resourceMap.entrySet()) {
//            Long key = entry.getKey();
//            Integer value = entry.getValue();
//            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
////            System.out.println("Key: " + key + ", Value: " + value);
//
//
//            //通过ID查对应资源表，将资源数据中的num累加再更新入库
//            ScenicResource scenicResource = scenicResourceMapper.selectById(key);
//
//            int rsNum = scenicResource.getResourceNum() + value;
//            scenicResource.setResourceNum(rsNum);
//            System.out.println(scenicResource.toString());
//            int i = scenicResourceMapper.updateById(scenicResource);
//
//            if(i == 0) {
//                return false;
//            }
//        }
//
//        return true;
//    }



}
