package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;

public class PluginValidate extends Plugin {

    @Override
    public void execute() throws PluginExecutionException {
        init();
        flyway.validate();
        logger.info(Messages.VALIDATION_SUCCESSFUL);
    }
}
