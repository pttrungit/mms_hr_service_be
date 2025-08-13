package com.mms.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MmsHrBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmsHrBackendApplication.class, args);
        System.out.println("🚀 MMS HR Backend started successfully!");
        System.out.println("📍 Server running at: http://localhost:8080");
        System.out.println("📋 API base URL: http://localhost:8080/api");
        System.out.println("🌐 Frontend URL: http://localhost:3000");
    }
}
