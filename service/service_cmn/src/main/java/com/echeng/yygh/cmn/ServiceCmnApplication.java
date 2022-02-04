package com.echeng.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 2:21 下午
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.echeng")

public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
