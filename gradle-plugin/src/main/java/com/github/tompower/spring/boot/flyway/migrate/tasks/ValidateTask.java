package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.PluginValidate;
import org.gradle.api.tasks.TaskAction;

public class ValidateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new PluginValidate());
    }

}