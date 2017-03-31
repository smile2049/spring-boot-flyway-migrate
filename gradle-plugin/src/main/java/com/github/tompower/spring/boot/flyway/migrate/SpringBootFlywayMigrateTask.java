package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.helper.LoggerGradleImpl;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;

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
        return getProject().getRootDir().getAbsolutePath();
    }

    private String getBuildDir() {
        return getProject().getBuildDir().getAbsolutePath();
    }
}
