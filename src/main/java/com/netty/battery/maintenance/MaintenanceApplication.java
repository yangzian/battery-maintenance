package com.netty.battery.maintenance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
@MapperScan("com.netty.battery.maintenance.mapper")
public class MaintenanceApplication
 {



    public static void main(String[] args) {
        SpringApplication.run(MaintenanceApplication.class, args);
    }



}
