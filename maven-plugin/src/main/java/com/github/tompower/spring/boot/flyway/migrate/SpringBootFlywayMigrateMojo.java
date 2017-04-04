package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.helper.LoggerMavenImpl;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.plugin.Plugin;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginFactory;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.List;

public abstract class SpringBootFlywayMigrateMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    protected MavenProject project;
    @Parameter(property = "profile")
    protected String profile;

    private FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    protected void execute(Plugin plugin) throws MojoExecutionException {
        try {
            PluginFactory.create(plugin,
                    getResourceDirectory(project),
                    getBuildDirectory(project),
                    profile,
                    logger).execute();
        } catch (PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }

    private String getResourceDirectory(MavenProject project) {
        Resource resource = (Resource) project.getBuild().getResources().get(0);
        return resource.getDirectory();
    }

    private List<String> getBuildDirectory(MavenProject project) {
        return Arrays.asList(project.getBuild().getOutputDirectory());
    }

}
