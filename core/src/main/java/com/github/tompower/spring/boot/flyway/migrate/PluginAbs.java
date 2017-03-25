package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class PluginAbs {

    protected final String resourceBaseDir;
    protected final List<String> paths;
    protected final String profile;
    protected final FlywayMigrateLogger logger;

    public PluginAbs(String resourceBaseDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        this.resourceBaseDir = resourceBaseDir;
        this.paths = paths;
        this.profile = profile;
        this.logger = logger;
    }

    protected Resources resources;
    protected Migration migration;
    protected Properties properties;

    public abstract void execute() throws PluginExecutionException;

    protected void init() throws PluginExecutionException {
        try {
            resources = Resources.getInstance(paths);
            properties = resources.getProperties(profile);
            migration = new Migration(properties, resourceBaseDir);
        } catch (IOException | URISyntaxException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }


}
