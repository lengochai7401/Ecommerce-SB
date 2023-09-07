package dev.khomuc.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"dev.khomuc.library.*", "dev.khomuc.admin.*"})
@EnableJpaRepositories(value = "dev.khomuc.library.repository")
@EntityScan(value = "dev.khomuc.library.model")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }



}
