package com.luanvan.ketquahoctapservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KetQuaHocTapServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KetQuaHocTapServiceApplication.class, args);
    }

}
