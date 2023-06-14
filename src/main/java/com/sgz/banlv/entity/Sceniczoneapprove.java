package com.sgz.banlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 景区审核表
 * </p>
 *
 * @author sgz
 * @since 2023-05-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sceniczoneapprove")
@ApiModel(value = "Sceniczoneapprove对象", description = "景区审核表")
public class Sceniczoneapprove implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicZoneApprove_id", type = IdType.AUTO)
    private Long scenicZoneApprove_id;

    @ApiModelProperty("外键 关联scenicZone表")
    private Long scenicZone_id;

    @ApiModelProperty("外键 关联agent表")
    private Long agent_id;

    private LocalTime scenicZoneApprove_submittime;

    @ApiModelProperty("0 已停用 1 等待审核 2 已启用")
    private Integer scenicZoneApprove_state;


}
