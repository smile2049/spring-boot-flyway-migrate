package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;

public class PluginFactory {

    public static Plugin create(Plugin plugin, String resourcesDir, String buildDir, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        plugin.setup(resourcesDir, buildDir, profile, logger);
        return plugin;
    }
}
