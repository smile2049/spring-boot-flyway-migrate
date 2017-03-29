package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import java.util.List;

public class PluginFactory {

    public static Plugin create(Plugin plugin, String resourcesDir, String targetDir, List<String> paths, String profile, FlywayMigrateLogger logger) throws PluginExecutionException {
        plugin.setup(resourcesDir, targetDir, paths, profile, logger);
        return plugin;
    }
}
