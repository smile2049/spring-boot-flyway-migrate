package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.SpringBootFlywayMigrateTask;
import org.gradle.api.tasks.TaskAction;

public class MigrateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void migrate() {
        super.execute(new PluginMigrate());
    }

}
