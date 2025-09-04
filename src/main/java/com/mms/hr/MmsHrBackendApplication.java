package com.mms.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableJpaAuditing
public class MmsHrBackendApplication {

    public static void main(String[] args) {
        // Chỉ chạy SpringApplication.run() một lần
        ApplicationContext context = SpringApplication.run(MmsHrBackendApplication.class, args);

        // Lấy bean Flyway
        Flyway flyway = context.getBean(Flyway.class);

        System.out.println("=== FlywayAutoMigrate: Clean DB + Migrate Flyweight data ===");
        flyway.clean();      // chỉ dùng dev/test
        flyway.migrate();    // migrate V1 → V7
        System.out.println("=== FlywayAutoMigrate: Migration completed ===");

        System.out.println("🚀 MMS HR Backend started successfully!");
        System.out.println("📍 Server running at: http://localhost:8080");
        System.out.println("📋 API base URL: http://localhost:8080/api");
        System.out.println("🌐 Frontend URL: http://localhost:3000");
    }
}
