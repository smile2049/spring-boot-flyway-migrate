package com.github.tompower.spring.boot.flyway.migrate.core.helper;

public interface FlywayMigrateLogger {

    public void info(String message);

    public void error(String message);

}
