package com.liyk.apps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liyongkui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/8 17:19
 */
@SpringBootApplication
//开启事务
@EnableTransactionManagement
public class AppsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppsApplication.class, args);
    }
}
