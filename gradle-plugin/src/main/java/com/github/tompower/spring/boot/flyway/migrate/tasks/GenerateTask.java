package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.PluginGenerate;
import com.github.tompower.spring.boot.flyway.migrate.SpringBootFlywayMigrateTask;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new PluginGenerate());
    }

}