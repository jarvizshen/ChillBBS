package com.chill.chillbbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Jarviz
 */
@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class ChillBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChillBbsApplication.class, args);
    }

}
