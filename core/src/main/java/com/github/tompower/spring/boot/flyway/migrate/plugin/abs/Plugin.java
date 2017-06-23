package com.github.tompower.spring.boot.flyway.migrate.plugin.abs;

import com.github.tompower.spring.boot.flyway.migrate.core.helper.FileHelper;
import com.github.tompower.spring.boot.flyway.migrate.core.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.core.FlywayFactory;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginClassLoader;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.PropertiesProvider;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.util.List;

public abstract class Plugin {

    public abstract void execute() throws PluginExecutionException;

    protected String resourcesDir;
    protected List<String> buildDirs;
    protected Properties properties;
    protected FlywayMigrateLogger logger;

    public void setup(String resourcesDir, List<String> buildDirs, Properties properties, FlywayMigrateLogger logger) throws PluginExecutionException {
        this.resourcesDir = resourcesDir;
        this.buildDirs = buildDirs;
        this.properties = properties;
        this.logger = logger;
        init();
    }

    protected abstract void init() throws PluginExecutionException;

    protected Flyway flyway;

    protected void defaultInit() throws PluginExecutionException {
        try {
            PluginClassLoader.updateClassLoader(FileHelper.getUrls(buildDirs));
            properties.putAll(PropertiesProvider.getProperties(FileHelper.getFiles(buildDirs), properties.getProperty("profile")));
            flyway = FlywayFactory.create(properties);
        } catch (IOException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }

}
