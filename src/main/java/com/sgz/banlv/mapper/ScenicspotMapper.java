package com.sgz.banlv.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sgz.banlv.entity.Scenicspot;
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
public interface ScenicspotMapper extends BaseMapper<Scenicspot> {

//    Integer  selectScenicspotNumByScenicspotId(@Param("scenicspotId") Long scenicspotId);


}
