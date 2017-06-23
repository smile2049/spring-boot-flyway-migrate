package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Generate;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Generate());
    }

}