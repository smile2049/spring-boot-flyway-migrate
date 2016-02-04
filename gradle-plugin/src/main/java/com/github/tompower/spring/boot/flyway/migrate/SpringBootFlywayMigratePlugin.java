package com.github.tompower.spring.boot.flyway.migrate;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class SpringBootFlywayMigratePlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        target.getTasks().create("flywayGenerate", GenerateTask.class);
        target.getTasks().create("flywayMigrate", MigrateTask.class);
    }

}

