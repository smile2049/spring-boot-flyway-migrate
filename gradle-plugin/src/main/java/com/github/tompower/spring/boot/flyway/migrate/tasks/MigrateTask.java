package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Migrate;
import org.gradle.api.tasks.TaskAction;

public class MigrateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Migrate());
    }

}
