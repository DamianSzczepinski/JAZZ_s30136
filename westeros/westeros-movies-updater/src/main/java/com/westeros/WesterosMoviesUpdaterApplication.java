package com.westeros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.westeros")
public class WesterosMoviesUpdaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WesterosMoviesUpdaterApplication.class, args);
    }

}
