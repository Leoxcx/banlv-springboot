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
@TableName("scenicmanage")
@ApiModel(value = "Scenicmanage对象", description = "")
public class Scenicmanage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicManage_id", type = IdType.AUTO)
    private Long scenicManage_id;

    private String scenicManage_name;

    private String scenicManage_password;

    private String scenicManage_phone;


}
