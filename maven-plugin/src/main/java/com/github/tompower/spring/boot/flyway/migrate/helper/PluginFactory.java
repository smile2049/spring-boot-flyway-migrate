package com.github.tompower.spring.boot.flyway.migrate.helper;

import com.github.tompower.spring.boot.flyway.migrate.Plugin;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.project.MavenProject;

public class PluginFactory {

    public static Plugin create(Plugin plugin, MavenProject project, String profile, FlywayMigrateLogger logger) throws DependencyResolutionRequiredException {
        plugin.setup(getResourceDirectory(project), getTargetDirectory(project), project.getCompileClasspathElements(), profile, logger);
        return plugin;
    }

    private static String getResourceDirectory(MavenProject project) {
        Resource resource = (Resource) project.getBuild().getResources().get(0);
        return resource.getDirectory();
    }

    private static String getTargetDirectory(MavenProject project) {
        return project.getBuild().getOutputDirectory();
    }
}
