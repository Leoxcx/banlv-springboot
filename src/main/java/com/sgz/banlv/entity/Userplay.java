package com.sgz.banlv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("userplay")
@ApiModel(value = "Userplay对象", description = "")
public class Userplay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userPlay_id", type = IdType.AUTO)
    private Long userPlay_id;

    private Long resource_id;

    private Long user_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime userPlay_time;

    private Double userPlay_to;


}
