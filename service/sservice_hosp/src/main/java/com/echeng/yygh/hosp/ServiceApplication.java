package com.echeng.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/1/28 4:15 下午
 */
@SpringBootApplication
// @ComponentScan(basePackages = "com.echeng")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
