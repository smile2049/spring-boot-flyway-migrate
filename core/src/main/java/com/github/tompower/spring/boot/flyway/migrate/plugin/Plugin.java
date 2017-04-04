package com.github.tompower.spring.boot.flyway.migrate.plugin;

import com.github.tompower.spring.boot.flyway.migrate.helper.FileHelper;
import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.internal.FlywayFactory;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginClassLoader;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesProvider;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.util.List;

public abstract class Plugin {

    public abstract void execute() throws PluginExecutionException;

    protected String resourcesDir;
    protected List<String> buildDirs;
    protected String profile;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, List<String> buildDirs, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        this.resourcesDir = resourcesDir;
        this.buildDirs = buildDirs;
        this.profile = profile;
        this.logger = logger;
        init();
    }

    protected abstract void init() throws PluginExecutionException;

    protected Properties properties;
    protected Flyway flyway;

    protected void defaultInit() throws PluginExecutionException {
        try {
            PluginClassLoader.updateClassLoader(FileHelper.getUrls(buildDirs));
            properties = PropertiesProvider.getProperties(FileHelper.getFiles(buildDirs), profile);
            flyway = FlywayFactory.create(properties);
        } catch (IOException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }

}
