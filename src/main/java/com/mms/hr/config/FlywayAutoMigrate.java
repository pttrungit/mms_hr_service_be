package com.mms.hr.config;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class FlywayAutoMigrate {

    private final Flyway flyway;

    public FlywayAutoMigrate(Flyway flyway) {
        this.flyway = flyway;
    }

    @PostConstruct
    public void migrate() {
        // Xóa sạch DB (dev/test)
        flyway.clean();
        // Chạy tất cả migration
        flyway.migrate();
    }
}
