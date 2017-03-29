package com.github.tompower.spring.boot.flyway.migrate.helper;

import com.github.tompower.spring.boot.flyway.migrate.Plugin;
import com.github.tompower.spring.boot.flyway.migrate.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.PluginFactory;
import java.io.File;
import static java.rmi.server.RemoteServer.getLog;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

public abstract class SpringBootFlywayMigrateTask extends DefaultTask {

    private final String profile = System.getProperty("profile");
    private FlywayMigrateLogger logger = new LoggerGradleImpl((Logger) getLog());

    protected void execute(Plugin plugin) {
        try {
            PluginFactory.create(plugin, getResourcesDir(), getTargetDir(), getPaths(), profile, logger).execute();
        } catch (PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private List<String> getPaths() {
        SourceSet mainSourceSet = findMainSourceSet();
        List<String> resources = mainSourceSet.getResources().getSrcDirs().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
        List<String> runtimeClassPath = mainSourceSet.getRuntimeClasspath().getFiles().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
        resources.addAll(runtimeClassPath);
        return resources;
    }

    private SourceSet findMainSourceSet() {
        for (SourceSet sourceSet : getJavaSourceSets(getProject())) {
            if (SourceSet.MAIN_SOURCE_SET_NAME.equals(sourceSet.getName())) {
                return sourceSet;
            }
        }
        return null;
    }

    private Iterable<SourceSet> getJavaSourceSets(Project project) {
        JavaPluginConvention plugin = project.getConvention()
                .getPlugin(JavaPluginConvention.class);
        if (plugin == null) {
            return Collections.emptyList();
        }
        return plugin.getSourceSets();
    }

    private String getResourcesDir() {
        return getProject().getRootDir().getAbsolutePath();
    }

    private String getTargetDir() {
        return getProject().getBuildDir().getAbsolutePath();
    }
}
