package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Baseline;
import org.gradle.api.tasks.TaskAction;

public class BaselineTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Baseline());
    }

}