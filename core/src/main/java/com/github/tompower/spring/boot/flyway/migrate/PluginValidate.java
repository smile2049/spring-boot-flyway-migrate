package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import java.util.List;
import org.flywaydb.core.Flyway;

public class PluginValidate extends PluginAbs {

    public PluginValidate(String baseDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        super(baseDir, paths, profile, logger);
    }

    @Override
    public void execute() throws PluginExecutionException {
        init();
        Flyway flyway = FlywayFactory.create(migration.getDirectory(), resources.getClassloader(), properties);
        flyway.validate();
        logger.info(Messages.VALIDATION_SUCCESSFUL);
    }
}
