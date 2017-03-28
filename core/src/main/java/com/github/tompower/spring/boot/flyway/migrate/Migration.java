package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.File;
import org.springframework.boot.jdbc.DatabaseDriver;

public class Migration {

    private final Properties properties;
    private final String resourcesDir;
    private final String targetDir;

    public Migration(Properties properties, String resourcesDir, String targetDir) {
        this.properties = properties;
        this.resourcesDir = resourcesDir;
        this.targetDir = targetDir;
    }

    /**
     * Get file for Flyway Migration     *
     * @param version
     * @return File
     */
    public File getFile(Integer version) {
        return new File(getResourcesDirectory() + getMigrationFile(version));
    }

    private String getMigrationFile(Integer version) {
        return MigrationValues.MIGRATION_FILE_PREFIX + String.valueOf(version) + MigrationValues.MIGRATION_FILE_SUFFIX;
    }

    /**
     * Get Flyway Migration directory
     * @return
     */
    public String getResourcesDirectory() {
        return resourcesDir + "/" + getMigrationDirectory();
    }
    /**
     * Get Flyway Migration target directory
     * @return
     */
    public String getTargetDirectory() {
        return targetDir + "/" + getMigrationDirectory();
    }

    private String getMigrationDirectory() {
        return getDirectoryBase() + getVendorDirectory() + "/";
    }

    private String getDirectoryBase() {
        return !"".equals(getFlywayLocationsDirectory()) ? getFlywayLocationsDirectory() : MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/";
    }

    private String getFlywayLocationsDirectory() {
        return properties.getFlywayLocations().replace(MigrationValues.VENDOR, "");
    }

    private String getVendorDirectory() {
        return isVendor() ? getVendor() + "/": "";
    }

    private String getVendor() {
        return DatabaseDriver.fromJdbcUrl(properties.getUrl()).getId();
    }

    private Boolean isVendor() {
        return properties.getFlywayLocations().endsWith(MigrationValues.VENDOR);
    }

}
