package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.File;
import org.springframework.boot.jdbc.DatabaseDriver;

public class Migration {

    private final Properties properties;
    private final String baseDir;

    public Migration(Properties properties, String baseDir) {
        this.properties = properties;
        this.baseDir = baseDir;
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
        return MigrationValues.MIGRATION_FILE_PREFIX + String.valueOf(version) + MigrationValues.MIGRATION_FILE_SUFFIX;
    }

    /**
     * Get Flyway Migration directory
     * @return
     */
    public String getDirectory() {
        return baseDir + "/" + getMigrationDirectory();
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
