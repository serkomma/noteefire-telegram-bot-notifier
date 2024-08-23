package com.serkomma.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.serkomma.*")
public class NotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifierApplication.class, args);
    }

}
