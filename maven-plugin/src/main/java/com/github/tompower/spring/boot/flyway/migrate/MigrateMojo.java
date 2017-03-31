package com.github.tompower.spring.boot.flyway.migrate;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Run Flyway migrations
 */
@Mojo(name = "migrate")
public class MigrateMojo extends SpringBootFlywayMigrateMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(property = "profile")
    private String profile;

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new PluginMigrate(), project, profile);
    }

}
