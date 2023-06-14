package com.sgz.banlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sceniczone_scenicspot")
@ApiModel(value = "SceniczoneScenicspot对象", description = "")
public class SceniczoneScenicspot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicZone_scenicSpot_id", type = IdType.AUTO)
    private Long scenicZone_scenicSpot_id;

    private Long scenicZone_id;

    private Long scenicSpot_id;

    private Boolean scenicZone_scenicSpot_use;


}
