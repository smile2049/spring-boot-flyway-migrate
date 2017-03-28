package com.github.tompower.spring.boot.flyway.migrate;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "generate")
public class GenerateMojo extends SpringBootFlywayMigrateAbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(property = "profile")
    private String profile;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            new PluginGenerate(getResourceDirectory(project), project.getCompileClasspathElements(), profile, logger).execute();
        } catch (DependencyResolutionRequiredException | PluginExecutionException e) {
            logger.error(e.getMessage());
            throw new MojoExecutionException(e.getMessage());
        }
    }

}
