package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.SpringBootFlywayMigrateTask;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new PluginGenerate());
    }

}