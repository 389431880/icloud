package com.project.icloudCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class IcloudCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcloudCoreApplication.class, args);
    }

}
