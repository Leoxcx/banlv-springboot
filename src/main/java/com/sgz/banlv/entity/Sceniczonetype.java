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
 * 景区分类表
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sceniczonetype")
@ApiModel(value = "Sceniczonetype对象", description = "景区分类表")
public class Sceniczonetype implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("景区分类编号")
    @TableId(value = "scenicZoneType_id", type = IdType.AUTO)
    private Integer scenicZoneType_id;

    @ApiModelProperty("景区分类描述")
    private String scenicZoneEntry_name;


}
