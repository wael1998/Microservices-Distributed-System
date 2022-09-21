package com.microcode.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.microcode.notification"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
