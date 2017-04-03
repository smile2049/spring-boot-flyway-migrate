package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FileHelper;
import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesProvider;
import org.flywaydb.core.Flyway;

import java.io.IOException;

public abstract class Plugin {

    public abstract void execute() throws PluginExecutionException;

    protected String resourcesDir;
    protected String buildDir;
    protected String profile;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, String buildDir, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        this.resourcesDir = resourcesDir;
        this.buildDir = buildDir;
        this.profile = profile;
        this.logger = logger;
        init();
    }

    protected abstract void init() throws PluginExecutionException;

    protected Properties properties;
    protected Flyway flyway;

    protected void defaultInit() throws PluginExecutionException {
        try {
            PluginClassloader.updateClassloader(FileHelper.getUrls(buildDir));
            properties = PropertiesProvider.getProperties(FileHelper.getFiles(buildDir), profile);
            flyway = FlywayFactory.create(properties);
        } catch (IOException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }

}
