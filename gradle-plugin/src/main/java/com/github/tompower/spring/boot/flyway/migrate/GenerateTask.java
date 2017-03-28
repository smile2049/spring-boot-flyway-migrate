package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.helper.LoggerGradleImpl;
import com.github.tompower.spring.boot.flyway.migrate.helper.SpringBootFlywayMigrateTask;
import java.util.List;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void generate() {
        try {
            List<String> paths = getPaths(getProject());
            FlywayMigrateLogger logger = new LoggerGradleImpl(getLogger());
            new PluginGenerate().execute();
        } catch (PluginExecutionException e) {
            e.printStackTrace();
        }
    }

}