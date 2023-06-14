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
 * 城市
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("city")
@ApiModel(value = "City对象", description = "城市")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("城市编号")
    @TableId(value = "city_id", type = IdType.AUTO)
    private Integer city_id;

    @ApiModelProperty("城市名称")
    private String city_name;

    @ApiModelProperty("城市简介")
    private String city_introduce;

    @ApiModelProperty("城市当前天气")
    private String city_weather;

    @ApiModelProperty("城市当前温度")
    private Double city_temperature;

    @ApiModelProperty("城市背景图片URL")
    private String city_bg;

    @ApiModelProperty("省份")
    private String city_province;


}
