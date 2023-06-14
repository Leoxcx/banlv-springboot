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
 * 景区
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sceniczone")
@ApiModel(value = "Sceniczone对象", description = "景区")
public class Sceniczone implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicZone_id", type = IdType.AUTO)
    private Long scenicZone_id;

    private Long scenicManage_id;

    private String scenicZone_name;

    @ApiModelProperty("景区介绍")
    private String scenicZone_introduce;

    @ApiModelProperty("所属城市编号")
    private Integer city_id;

    @ApiModelProperty("景区分类编号")
    private Integer scenicZoneType_id;

    @ApiModelProperty("景区评分")
    private Double scenicZone_score;

    @ApiModelProperty("景区评级")
    private String scenicZone_grade;

    @ApiModelProperty("地址")
    private String scenicZone_location;

    @ApiModelProperty("累计观看人数")
    private Integer scenicZone_number;

    @ApiModelProperty("经度")
    private Double scenicZone_longitude;

    @ApiModelProperty("纬度")
    private Double scenicZone_latitude;

    @ApiModelProperty("热度")
    private Double scenicZone_hot;

    @ApiModelProperty("对应图片表id ")
    private String scenicZone_bg;

    @ApiModelProperty("是否可用 ")
    private Boolean scenicZone_use;


}
