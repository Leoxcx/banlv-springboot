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
 * 属性表
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("attribute")
@ApiModel(value = "Attribute对象", description = "属性表")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attrId", type = IdType.AUTO)
    private Integer attrId;

    private String attrTable;

    private String attrValue;

    private String attrName;

    private Boolean attrShow;


}
