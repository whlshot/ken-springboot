package com.ken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.ken.mapper")
public class KenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KenApplication.class, args);
    }
}
