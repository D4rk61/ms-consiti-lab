package dev.jose.mssqsspring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsSqsSpring1Application {

    public static void main(String[] args) {
        SpringApplication.run(MsSqsSpring1Application.class, args);
    }

}
