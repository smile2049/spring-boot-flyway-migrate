package com.github.tompower.spring.boot.flyway.migrate.helper;

public class LoggerGradleImpl implements FlywayMigrateLogger {

    private final org.gradle.api.logging.Logger logger;

    public LoggerGradleImpl(org.gradle.api.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }
}
