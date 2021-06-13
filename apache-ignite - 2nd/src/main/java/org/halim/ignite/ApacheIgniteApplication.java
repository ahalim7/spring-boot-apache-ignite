package org.halim.ignite;

import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIgniteRepositories
public class ApacheIgniteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheIgniteApplication.class, args);
    }

}
