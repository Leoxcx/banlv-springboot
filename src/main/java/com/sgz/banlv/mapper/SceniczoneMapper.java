package com.sgz.banlv.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sgz.banlv.entity.Sceniczone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 景区 Mapper 接口
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Mapper
public interface SceniczoneMapper extends BaseMapper<Sceniczone> {

//    Integer selectSceniczoneNumberBySceniczoneId(@Param("sceniczoneId") Long sceniczoneId);


}
