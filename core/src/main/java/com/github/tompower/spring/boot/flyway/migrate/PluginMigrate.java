package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;

public class PluginMigrate extends Plugin {

    @Override
    public void execute() throws PluginExecutionException {
        int migrations = flyway.migrate();
        logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
    }

    @Override
    protected void init() throws PluginExecutionException {
        super.defaultInit();
    }

}
