package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerGradleImpl;
import java.util.List;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void generate() {
        try {
            List<String> paths = getPaths(getProject());
            FlywayMigrateLogger logger = new LoggerGradleImpl(getLogger());
            new PluginGenerate(getResourcesDir(), getTargetDir(), paths, profile, logger).execute();
        } catch (PluginExecutionException e) {
            e.printStackTrace();
        }
    }

}