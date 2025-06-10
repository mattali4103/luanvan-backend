package com.luanvan.hocphanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HocPhanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HocPhanServiceApplication.class, args);
    }

}
