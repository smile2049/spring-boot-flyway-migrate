package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.File;
import org.springframework.boot.jdbc.DatabaseDriver;

public class Migration {

    private final String DEFAULT_MIGRATION_DIRECTORY = "db/migration";
    private final String MIGRATION_FILE_PREFIX = "V";
    private final String MIGRATION_FILE_SUFFIX = "__migration.sql";
    private final String VENDOR = "{vendor}";

    private final Properties properties;
    private final String resourceBaseDir;

    public Migration(Properties properties, String resourceBaseDir) {
        this.properties = properties;
        this.resourceBaseDir = resourceBaseDir;
    }

    /**
     * Get file for Flyway Migration     *
     * @param version
     * @return File
     */
    public File getFile(Integer version) {
        return new File(getDirectory() + getMigrationFile(version));
    }

    private String getMigrationFile(Integer version) {
        return MIGRATION_FILE_PREFIX + String.valueOf(version) + MIGRATION_FILE_SUFFIX;
    }

    /**
     * Get Flyway Migration directory
     * @return
     */
    public String getDirectory() {
        return resourceBaseDir + "/" + getDirectoryBase() + "/" + getVendorDirectory();

    }

    private String getDirectoryBase() {
        return "".equals(getFlywayLocationsDirectory()) ? getFlywayLocationsDirectory() : DEFAULT_MIGRATION_DIRECTORY;
    }

    private String getFlywayLocationsDirectory() {
        return properties.getFlywayLocations().replace(VENDOR, "");
    }

    private String getVendorDirectory() {
        return isVendor() ? getVendor() + "/" : "";
    }

    private String getVendor() {
        return DatabaseDriver.fromJdbcUrl(properties.getUrl()).getId();
    }

    private Boolean isVendor() {
        return properties.getFlywayLocations().endsWith(VENDOR);
    }

}
