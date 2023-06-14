package com.sgz.banlv.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenicspotDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;

    private String scenicSpot_bg;

    private String scenicSpot_name;

    private Double scenicSpot_longitude;

    private Double scenicSpot_latitude;

    private String scenicSpot_remark;

}
