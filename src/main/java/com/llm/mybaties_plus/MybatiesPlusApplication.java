package com.llm.mybaties_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.llm.mybaties_plus.dao")
public class MybatiesPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatiesPlusApplication.class, args);
    }

}
