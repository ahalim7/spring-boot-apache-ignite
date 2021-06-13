package org.halim.ignite;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableIgniteRepositories
public class ApacheIgniteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheIgniteApplication.class, args);
    }

}
