package com.taotu.hv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//@MapperScan("com.taotu.hv.mapper")  // 确保扫描到 mapper 包
public class TaotuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotuApplication.class, args);
    }

}
