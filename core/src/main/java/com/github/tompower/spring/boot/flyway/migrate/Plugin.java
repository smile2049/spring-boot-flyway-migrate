package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.flywaydb.core.Flyway;

public abstract class Plugin {

    protected String resourcesDir;
    protected String targetDir;
    protected List<String> paths;
    protected String profile;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, String targetDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        this.resourcesDir = resourcesDir;
        this.targetDir = targetDir;
        this.paths = paths;
        this.profile = profile;
        this.logger = logger;
    }

    protected Resources resources;
    protected Migration migration;
    protected Properties properties;
    protected Flyway flyway;

    public abstract void execute() throws PluginExecutionException;

    protected void init() throws PluginExecutionException {
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
