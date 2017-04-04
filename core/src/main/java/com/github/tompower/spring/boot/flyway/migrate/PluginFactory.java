package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import java.util.List;

public class PluginFactory {

    public static Plugin create(Plugin plugin, String resourcesDir, List<String> buildDirs, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        plugin.setup(resourcesDir, buildDirs, profile, logger);
        return plugin;
    }
}
