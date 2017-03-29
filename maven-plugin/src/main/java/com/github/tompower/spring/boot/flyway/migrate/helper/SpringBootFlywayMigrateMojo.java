package com.github.tompower.spring.boot.flyway.migrate.helper;

import com.github.tompower.spring.boot.flyway.migrate.Plugin;
import com.github.tompower.spring.boot.flyway.migrate.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.PluginFactory;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

public abstract class SpringBootFlywayMigrateMojo extends AbstractMojo {

    private FlywayMigrateLogger logger = new LoggerMavenImpl(getLog());

    protected void execute(Plugin plugin, MavenProject project, String profile) throws MojoExecutionException {
        try {
            String resourceDirectory = getResourceDirectory(project);
            String targetDirectory = getTargetDirectory(project);
            List paths = project.getCompileClasspathElements();
            PluginFactory.create(plugin, resourceDirectory, targetDirectory, paths, profile, logger).execute();
        } catch (DependencyResolutionRequiredException | PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }

    private String getResourceDirectory(MavenProject project) {
        Resource resource = (Resource) project.getBuild().getResources().get(0);
        return resource.getDirectory();
    }

    private String getTargetDirectory(MavenProject project) {
        return project.getBuild().getOutputDirectory();
    }

}
