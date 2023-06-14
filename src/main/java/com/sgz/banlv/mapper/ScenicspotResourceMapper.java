package com.sgz.banlv.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sgz.banlv.entity.ScenicspotResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Mapper
public interface ScenicspotResourceMapper extends BaseMapper<ScenicspotResource> {

//    Long selectScenicspotIdByResourceId(@Param("resourceId") Long resourceId);

//    List<ScenicspotResource> selectResource_idByScenicSpot_idAndScenicSpot_resource_useTrue(@Param("scenicSpot_id") Long scenicSpot_id);

//    List<ScenicspotResource> selectAllByScenicSpot_idAndScenicSpot_resource_useTrue(@Param("scenicSpot_id") Long scenicSpot_id);
}
