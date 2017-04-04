package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.tasks.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SpringBootFlywayMigratePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().create("flywayGenerate", GenerateTask.class);
        project.getTasks().create("flywayMigrate", MigrateTask.class);
        project.getTasks().create("flywayClean", CleanTask.class);
        project.getTasks().create("flywayInfo", InfoTask.class);
        project.getTasks().create("flywayValidate", ValidateTask.class);
        project.getTasks().create("flywayBaseline", BaselineTask.class);
        project.getTasks().create("flywayRepair", RepairTask.class);
    }

}

