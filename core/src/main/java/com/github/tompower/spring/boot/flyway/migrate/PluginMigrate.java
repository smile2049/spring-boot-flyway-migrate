package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import java.util.List;
import org.flywaydb.core.Flyway;

public class PluginMigrate extends PluginAbs {

    public PluginMigrate(String resourceBaseDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        super(resourceBaseDir, paths, profile, logger);
    }

    public void execute() throws PluginExecutionException {
        init();
        Flyway flyway = FlywayFactory.create(new String[]{migration.getDirectory()}, resources.getClassloader(), properties);
        int migrations = flyway.migrate();
        logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
    }

}
