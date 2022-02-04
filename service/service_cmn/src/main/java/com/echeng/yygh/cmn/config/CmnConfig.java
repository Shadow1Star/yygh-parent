package com.echeng.yygh.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/1/28 4:45 下午
 */
@Configuration
@MapperScan("com.echeng.yygh.cmn.mapper")
public class CmnConfig {
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
