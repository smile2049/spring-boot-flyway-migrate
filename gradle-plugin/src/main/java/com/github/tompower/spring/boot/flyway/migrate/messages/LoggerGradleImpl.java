package com.github.tompower.spring.boot.flyway.migrate.messages;

public class LoggerGradleImpl implements FlywayMigrateLogger {

    private final org.gradle.api.logging.Logger logger;

    public LoggerGradleImpl(org.gradle.api.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        System.out.println(message);
//        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }
}
