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
 * 景区词条中间表
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sceniczone_sceniczoneentry")
@ApiModel(value = "SceniczoneSceniczoneentry对象", description = "景区词条中间表")
public class SceniczoneSceniczoneentry implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("景区词条中间编号")
    @TableId(value = "scenicZone_scenicZoneEntry_id", type = IdType.AUTO)
    private Long scenicZone_scenicZoneEntry_id;

    @ApiModelProperty("景区词条编号")
    private Integer scenicZoneEntry_id;

    @ApiModelProperty("景区编号")
    private Long scenicZone_id;

    @ApiModelProperty("是否启用")
    private Boolean scenicZone_scenicSpot_use;


}
