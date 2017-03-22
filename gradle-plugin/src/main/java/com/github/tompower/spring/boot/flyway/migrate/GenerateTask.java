package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerGradleImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.gradle.api.tasks.TaskAction;

public class GenerateTask extends SpringBootFlywayMigrateTask {

    @TaskAction
    public void generate() throws URISyntaxException, IOException, SQLException, DependencyResolutionRequiredException {
        List<String> paths = getPaths(getProject());
        FlywayMigrateLogger logger = new LoggerGradleImpl(getLogger());
        new PluginGenerate(getProject().getRootDir().getAbsolutePath(), paths, profile, logger).generate();
    }

}
