package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.LoggerMavenImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "generate")
public class GenerateMojo extends AbstractMojo {

    private FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(property = "profile")
    private String profile;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            Resource resource = (Resource) project.getBuild().getResources().get(0);
            new PluginGenerate(resource.getDirectory(), project.getCompileClasspathElements(), profile, logger).generate();
        } catch (DependencyResolutionRequiredException | IOException | SQLException | URISyntaxException e) {
            logger.error(e.getMessage());
            throw new MojoExecutionException(e.getMessage());
        }
    }

}
