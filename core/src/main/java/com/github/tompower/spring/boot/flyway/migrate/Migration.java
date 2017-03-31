package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;

import java.io.File;

public class Migration {

    private final Properties properties;
    private final String resourcesDir;

    public Migration(Properties properties, String resourcesDir) {
        this.properties = properties;
        this.resourcesDir = resourcesDir;
    }

    /**
     * Get file for Flyway Migration
     * @param version
     * @return File
     */
    public File getFile(Integer version) {
        return new File(getResourcesDirectory() + getMigrationFile(version));
    }

    private String getMigrationFile(Integer version) {
        return MigrationValues.MIGRATION_FILE_PREFIX + String.valueOf(version) + MigrationValues.MIGRATION_FILE_SUFFIX;
    }

    private String getResourcesDirectory() {
        return resourcesDir + "/" + getMigrationDirectory() + "/";
    }

    private String getMigrationDirectory() {
        String flywayLocationDirectory = properties.getFlywayLocations()[0];
        return flywayLocationDirectory != "" ? flywayLocationDirectory : MigrationValues.DEFAULT_MIGRATION_DIRECTORY;
    }

}
