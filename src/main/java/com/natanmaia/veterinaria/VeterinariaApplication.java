package com.natanmaia.veterinaria;

import com.natanmaia.veterinaria.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
@EnableAutoConfiguration
@ComponentScan
public class VeterinariaApplication {


    public static void main(String[] args) {
        SpringApplication.run(VeterinariaApplication.class, args);
    }

}
