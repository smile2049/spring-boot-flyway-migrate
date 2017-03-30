package com.github.tompower.spring.boot.flyway.migrate.helper;

import com.github.tompower.spring.boot.flyway.migrate.Plugin;
import com.github.tompower.spring.boot.flyway.migrate.PluginExecutionException;
import com.github.tompower.spring.boot.flyway.migrate.PluginFactory;
import static java.rmi.server.RemoteServer.getLog;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;

public abstract class SpringBootFlywayMigrateTask extends DefaultTask {

    private final String profile = System.getProperty("profile");
    private FlywayMigrateLogger logger = new LoggerGradleImpl((Logger) getLog());

    public abstract void action();

    protected void execute(Plugin plugin) {
        try {
            PluginFactory.create(plugin, getResourcesDir(), getTargetDir(), profile, logger).execute();
        } catch (PluginExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getResourcesDir() {
        return getProject().getRootDir().getAbsolutePath();
    }

    private String getTargetDir() {
        return getProject().getBuildDir().getAbsolutePath();
    }
}
