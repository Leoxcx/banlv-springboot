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
import org.springframework.stereotype.Component;

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
@Component
@TableName("scenicspot_resource")
@ApiModel(value = "ScenicspotResource对象", description = "")
public class ScenicspotResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "scenicSpot_resource_id", type = IdType.AUTO)
    private Long scenicSpot_resource_id;

    private Long scenicSpot_id;

    private Long resource_id;

    private Boolean scenicSpot_resource_use;


}
