package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;

public class PluginMigrate {

    private final FlywayMigrateLogger logger;
    private final List<String> paths;
    private final String profile;

    public PluginMigrate(List<String> paths, String profile, FlywayMigrateLogger logger) {
        this.logger = logger;
        this.paths = paths;
        this.profile = profile;
    }

    public void migrate() {
        try {
            Resources resources = Resources.getInstance(paths);
            int migrations = Migrate.flywayMigrate(resources.getClassloader(), resources.getProperties(profile));
            logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
        } catch (DependencyResolutionRequiredException | IOException | URISyntaxException ex) {
            logger.error(ex.getMessage());
        }
    }
}
