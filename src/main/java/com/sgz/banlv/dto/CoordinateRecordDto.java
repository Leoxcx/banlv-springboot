package com.sgz.banlv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateRecordDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long agent_id;

    private Integer city_id;

    private String scenicZone_name;

    private List<ScenicspotDto> scenicspotDto;

}
