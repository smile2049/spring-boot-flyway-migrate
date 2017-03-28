package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import java.util.List;

public class PluginMigrate extends PluginAbs {

    public PluginMigrate(String resourcesDir, String targetDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        super(resourcesDir, targetDir, paths, profile, logger);
    }

    public void execute() throws PluginExecutionException {
        init();
        int migrations = flyway.migrate();
        logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
    }

}
