package com.github.tompower.spring.boot.flyway.migrate.tasks;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Info;
import org.gradle.api.tasks.TaskAction;

public class InfoTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void action() {
        super.execute(new Info());
    }

}