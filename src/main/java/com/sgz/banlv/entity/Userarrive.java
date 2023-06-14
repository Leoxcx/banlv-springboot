package com.sgz.banlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
@TableName("userarrive")
@ApiModel(value = "Userarrive对象", description = "")
public class Userarrive implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userArrive_id", type = IdType.AUTO)
    private Long userArrive_id;

    private Long user_id;

    private Long scenicSpot_id;

    private Timestamp userArrive_time;


}
