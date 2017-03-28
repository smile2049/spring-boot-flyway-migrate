package com.github.tompower.spring.boot.flyway.migrate.helper;



public class LoggerMavenImpl implements FlywayMigrateLogger {

    private final org.apache.maven.plugin.logging.Log log;

    public LoggerMavenImpl(org.apache.maven.plugin.logging.Log log) {
        this.log = log;
    }

    @Override
    public void info(String message) {
        log.info("");
        log.info(Messages.LINE + Messages.SPACE + message + Messages.SPACE + Messages.LINE);
        log.info("");
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

}
