package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.PluginGenerate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "generate")
public class GenerateMojo extends SpringBootFlywayMigrateMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    @Parameter(property = "profile")
    private String profile;

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new PluginGenerate(), project, profile);
    }

}
