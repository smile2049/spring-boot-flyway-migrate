package com.github.tompower.spring.boot.flyway.migrate;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;

public abstract class GradleFlywayMigrateTask extends JavaExec {

    protected final String profile = System.getProperty("profile");

    protected List<String> getPaths(Project project) {
        SourceSet mainSourceSet = findMainSourceSet(project);
        List<String> resources = mainSourceSet.getResources().getSrcDirs().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
        List<String> runtimeClassPath = mainSourceSet.getRuntimeClasspath().getFiles().stream()
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
        resources.addAll(runtimeClassPath);
        return resources;
    }

    private static SourceSet findMainSourceSet(Project project) {
        for (SourceSet sourceSet : getJavaSourceSets(project)) {
            if (SourceSet.MAIN_SOURCE_SET_NAME.equals(sourceSet.getName())) {
                return sourceSet;
            }
        }
        return null;
    }

    private static Iterable<SourceSet> getJavaSourceSets(Project project) {
        JavaPluginConvention plugin = project.getConvention()
                .getPlugin(JavaPluginConvention.class);
        if (plugin == null) {
            return Collections.emptyList();
        }
        return plugin.getSourceSets();
    }

}
