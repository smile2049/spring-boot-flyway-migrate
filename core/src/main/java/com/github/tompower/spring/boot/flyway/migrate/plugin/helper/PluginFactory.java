package com.github.tompower.spring.boot.flyway.migrate.plugin.helper;

import com.github.tompower.spring.boot.flyway.migrate.core.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.plugin.abs.Plugin;

import java.util.List;
import java.util.Properties;

public class PluginFactory {

    public static Plugin create(Plugin plugin, String resourcesDir, List<String> buildDirs, Properties properties, FlywayMigrateLogger logger) throws PluginExecutionException {
        plugin.setup(resourcesDir, buildDirs, getProperties(properties), logger);
        return plugin;
    }

    private static com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties getProperties(Properties properties) {
        com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties sbfmProperties = new com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties();
        sbfmProperties.putAll(properties);
        return sbfmProperties;
    }
}
