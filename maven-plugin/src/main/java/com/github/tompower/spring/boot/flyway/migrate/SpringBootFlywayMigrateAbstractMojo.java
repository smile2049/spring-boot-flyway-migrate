package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerMavenImpl;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

public abstract class SpringBootFlywayMigrateAbstractMojo extends AbstractMojo {

    protected FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    protected String getResourceDirectory(MavenProject project) {
        Resource resource = (Resource) project.getBuild().getResources().get(0);
        return resource.getDirectory();
    }

    protected String getTargetDirectory(MavenProject project) {
        return project.getBuild().getOutputDirectory();
    }

}
