package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Clean;
import org.gradle.api.tasks.TaskAction;

public class CleanTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Clean());
    }

}