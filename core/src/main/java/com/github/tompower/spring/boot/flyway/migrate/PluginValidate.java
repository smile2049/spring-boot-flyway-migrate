package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;

public class PluginValidate extends Plugin {

    @Override
    public void execute() throws PluginExecutionException {
        flyway.validate();
        logger.info(Messages.VALIDATION_SUCCESSFUL);
    }

    @Override
    protected void init() throws PluginExecutionException {
        super.defaultInit();
    }
}
