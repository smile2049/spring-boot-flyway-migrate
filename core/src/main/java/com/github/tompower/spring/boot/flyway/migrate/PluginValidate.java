package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import java.util.List;

public class PluginValidate extends PluginAbs {

    public PluginValidate(String resourcesDir, String targetDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        super(resourcesDir, targetDir, paths, profile, logger);
    }

    @Override
    public void execute() throws PluginExecutionException {
        init();
        flyway.validate();
        logger.info(Messages.VALIDATION_SUCCESSFUL);
    }
}
