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
 * 
 * </p>
 *
 * @author sgz
 * @since 2023-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_resource")
@ApiModel(value = "UserResource对象", description = "")
public class UserResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_resource_id", type = IdType.AUTO)
    private Long user_resource_id;

    private Long user_id;

    private Long resource_id;


}
