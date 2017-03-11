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
    private final Migration migration;

    public PluginMigrate(FlywayMigrateLogger logger, List<String> paths, String profile, Migration migration) {
        this.logger = logger;
        this.paths = paths;
        this.profile = profile;
        this.migration = migration;
    }

    public void migrate() {
        try {
            Resources resources = Resources.getInstance(paths);
            String[] locations = {migration.getDirectory()};
            int migrations = Migrator.flywayMigrate(locations, resources.getClassloader(), resources.getProperties(profile));
            logger.info(Messages.MIGRATION_SUCCESSFUL + Messages.getMigrationMessage(migrations));
        } catch (DependencyResolutionRequiredException | IOException | URISyntaxException ex) {
            logger.error(ex.getMessage());
        }
    }
}
