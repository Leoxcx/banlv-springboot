package com.sgz.banlv.dto;


import com.sgz.banlv.entity.ScenicResource;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ScenicSpotInfoDto implements Serializable {
    private Long scenicSpot_id;
    private String scenicSpot_name;
    private Double scenicSpot_longitude;
    private Double scenicSpot_latitude;
    private Double scenicSpot_range;
    private Integer scenicSpot_num;

    private List<ScenicResource> resources;


}
