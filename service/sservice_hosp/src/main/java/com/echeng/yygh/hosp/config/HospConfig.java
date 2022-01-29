package com.echeng.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/1/28 4:45 下午
 */
@Configuration
@MapperScan("com.echeng.yygh.hosp.mapper")
public class HospConfig {
}
