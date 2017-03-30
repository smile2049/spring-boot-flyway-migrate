package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesProvider;
import java.io.IOException;
import org.flywaydb.core.Flyway;

public abstract class Plugin {

    public abstract void execute() throws PluginExecutionException;

    private String resourcesDir;
    private String targetDir;
    private String profile;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, String targetDir, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        this.resourcesDir = resourcesDir;
        this.targetDir = targetDir;
        this.profile = profile;
        this.logger = logger;
        init();
    }

    protected abstract void init() throws PluginExecutionException;

    Resources resources;
    Migration migration;
    Properties properties;
    Flyway flyway;

    void defaultInit() throws PluginExecutionException {
        try {
            resources = new Resources(targetDir);
            PluginClassloader.updateClassloader(resources.getUrls());
            properties = PropertiesProvider.getProperties(resources.getFiles(), profile);
            migration = new Migration(properties, resourcesDir, targetDir);
            flyway = FlywayFactory.create(migration.getTargetDirectory(), properties);
        } catch (IOException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }


}
