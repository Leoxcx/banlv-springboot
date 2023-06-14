package com.sgz.banlv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.sgz.banlv.entity") // 指定实体类所在的包
@MapperScan("com.sgz.banlv.mapper")
public class BanlvApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanlvApplication.class, args);
    }

}
