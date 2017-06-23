package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Repair;
import org.gradle.api.tasks.TaskAction;

public class RepairTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Repair());
    }

}