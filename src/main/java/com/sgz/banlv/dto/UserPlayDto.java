package com.sgz.banlv.dto;

import com.sgz.banlv.entity.Userplay;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
@Slf4j
@Data
public class UserPlayDto implements Serializable {
    private Long resource_id;
    private Long user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long userPlay_time;


}
