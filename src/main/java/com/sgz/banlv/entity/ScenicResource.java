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
@TableName("scenicResource")
@ApiModel(value = "Resource对象", description = "")
public class ScenicResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resource_id;

    private Long agent_id;

    private String resource_name;

    private String resource_url;

    private String resource_thumbnail;

    private Double resource_money;

    @ApiModelProperty("资源类型，全景图为1，全景视频为2")
    private Integer resource_type;

    @ApiModelProperty("资源播放数")
    private Integer resource_num;

    @ApiModelProperty("资源解说词")
    private String resource_commentary;

    @ApiModelProperty("资源解说音频")
    private String resource_commentaryaudio;



}
