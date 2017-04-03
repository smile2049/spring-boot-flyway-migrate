package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.helper.LoggerGradleImpl;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

public abstract class SpringBootFlywayMigrateTask extends DefaultTask {

    private final String profile = System.getProperty("profile");
    private FlywayMigrateLogger logger = new LoggerGradleImpl((Logger) getLogger());

    public abstract void action();

    protected void execute(Plugin plugin) {
        try {
            PluginFactory.create(plugin, getResourcesDir(), getBuildDir(), profile, logger).execute();
        } catch (PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getResourcesDir() {
        SourceSet main = getProject().getConvention().getPlugin(JavaPluginConvention.class).getSourceSets().getByName("main");
        return main.getResources().getSrcDirs().toString();
    }

    private String getBuildDir() {
        return getProject().getBuildDir().getAbsolutePath();
    }
}
