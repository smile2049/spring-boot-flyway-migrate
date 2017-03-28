package com.github.tompower.spring.boot.flyway.migrate.helper;

import com.github.tompower.spring.boot.flyway.migrate.Plugin;
import com.github.tompower.spring.boot.flyway.migrate.PluginExecutionException;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

public abstract class SpringBootFlywayMigrateAbstractMojo extends AbstractMojo {

    protected FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    public void execute(Plugin plugin, MavenProject project, String profile) throws MojoExecutionException {
        try {
            PluginFactory.create(plugin, project, profile, logger).execute();
        } catch (DependencyResolutionRequiredException | PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }

}
