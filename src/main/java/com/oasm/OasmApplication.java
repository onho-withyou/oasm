package com.oasm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.oasm.dao")
public class OasmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasmApplication.class, args);
    }

}
