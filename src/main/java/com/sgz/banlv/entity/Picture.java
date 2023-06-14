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
 * 图片资源表
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("picture")
@ApiModel(value = "Picture对象", description = "图片资源表")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片编号")
    @TableId(value = "picture_id", type = IdType.AUTO)
    private Long picture_id;

    @ApiModelProperty("图片名称")
    private String picture_name;

    @ApiModelProperty("图片路径")
    private String picture_url;

    @ApiModelProperty("图片缩略图")
    private String picture_thumbnail;

    @ApiModelProperty("是否启用")
    private Boolean picture_use;

    @ApiModelProperty("图片备注")
    private String picture_remark;


}
