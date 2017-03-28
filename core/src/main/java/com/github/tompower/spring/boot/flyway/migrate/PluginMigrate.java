package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;

public class PluginMigrate extends Plugin {

    public void execute() throws PluginExecutionException {
        init();
        int migrations = flyway.migrate();
        logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
    }

}
