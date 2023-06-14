package com.sgz.banlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("scenicspot")
@ApiModel(value = "Scenicspot对象", description = "")
public class Scenicspot implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicSpot_id", type = IdType.AUTO)
    private Long scenicSpot_id;

    private String scenicSpot_name;

    private Double scenicSpot_longitude;

    private Double scenicSpot_latitude;

    private Double scenicSpot_range;

    @ApiModelProperty("景点播放次数")
    private Integer scenicSpot_num;

    @ApiModelProperty("景点介绍")
    private String scenicSpot_introduce;

    @ApiModelProperty("景点缩略图")
    private String scenicSpot_thumbnail;


}
