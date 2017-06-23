package com.github.tompower.spring.boot.flyway.migrate.core;

import com.github.tompower.spring.boot.flyway.migrate.core.helper.MigrationHelper;
import com.github.tompower.spring.boot.flyway.migrate.core.helper.MigrationValues;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties;

import java.io.File;

public class Migration {

    private final Properties properties;
    private final String resourcesDir;

    public Migration(Properties properties, String resourcesDir) {
        this.properties = properties;
        this.resourcesDir = resourcesDir;
    }

    /**
     * Get file for Flyway Migration using version and description
     *
     * @param version
     * @param description
     * @return File
     */
    public File getFile(Integer version, String description) {
        return new File(getResourcesDirectory() + getMigrationFile(version, description));
    }


    private String getMigrationFile(Integer version, String description) {
        return MigrationHelper.getMigrationFileName(version, description);
    }

    private String getResourcesDirectory() {
        return resourcesDir + "/" + getMigrationDirectory() + "/";
    }

    private String getMigrationDirectory() {
        String flywayLocationDirectory = properties.getFlywayLocations()[0];
        return flywayLocationDirectory != "" ? flywayLocationDirectory : MigrationValues.DEFAULT_MIGRATION_DIRECTORY;
    }

}
