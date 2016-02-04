package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerGradleImpl;
import java.util.List;

public class GenerateTask extends GradleFlywayMigrateTask {

    @Override
    public void exec() {
        List<String> paths = getPaths(getProject());
        FlywayMigrateLogger logger = new LoggerGradleImpl(getLogger());
        new PluginGenerate(paths, profile, logger).generate();
    }

}
