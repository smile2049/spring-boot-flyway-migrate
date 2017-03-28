package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerGradleImpl;
import java.util.List;
import org.gradle.api.tasks.TaskAction;

public class MigrateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void migrate() {
        try {
            List<String> paths = getPaths(getProject());
            FlywayMigrateLogger logger = new LoggerGradleImpl(getLogger());
            new PluginMigrate(getResourcesDir(), getTargetDir(), paths, profile, logger).execute();
        } catch (PluginExecutionException e) {
            e.printStackTrace();
        }
    }

}
