package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerMavenImpl;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Run Flyway migrations
 */
@Mojo(name = "migrate")
public class MigrateMojo extends AbstractMojo {

    private FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(property = "profile")
    private String profile;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            new PluginMigrate(project.getBasedir().getAbsolutePath(), project.getCompileClasspathElements(), profile, logger).execute();
        } catch (DependencyResolutionRequiredException | PluginExecutionException e) {
            logger.error(e.getMessage());
        }
    }

}
