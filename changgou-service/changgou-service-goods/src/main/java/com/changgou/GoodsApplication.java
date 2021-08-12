package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication
@EnableEurekaClient // 开启Eureka客户端
@MapperScan(basePackages = {"com.changgou.dao"})
public class GoodsApplication {

    public static void main(String[] args){
        SpringApplication.run(GoodsApplication.class, args);
    }

}
