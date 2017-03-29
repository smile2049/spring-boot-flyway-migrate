package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.flywaydb.core.Flyway;

public abstract class Plugin {

    public abstract void execute() throws PluginExecutionException;

    private String resourcesDir;
    private String targetDir;
    private List<String> paths;
    private String profile;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, String targetDir, List<String> paths, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        this.resourcesDir = resourcesDir;
        this.targetDir = targetDir;
        this.paths = paths;
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
            resources = Resources.getInstance(paths);
            properties = resources.getProperties(profile);
            migration = new Migration(properties, resourcesDir, targetDir);
            flyway = FlywayFactory.create(migration.getTargetDirectory(), resources.getClassloader(), properties);
        } catch (IOException | URISyntaxException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }


}
