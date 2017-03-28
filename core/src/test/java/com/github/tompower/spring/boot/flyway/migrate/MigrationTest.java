package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesValues;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MigrationTest {

    private String baseDir = "/resourcesDir";
    private String targetDir = "/targetDir";
    private String defaultDir = baseDir + "/" + MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/";

    @Test
    public void testDefaultDirectory() throws Exception {
        Migration blankMigration = getMigration(getBlankProperties());
        assertEquals(defaultDir, blankMigration.getResourcesDirectory());
    }

    @Test
    public void testVendorDirectory() throws Exception {
        Migration defaultVendorMigration = getMigration(getProperties(getDefaultVendorProperties()));
        assertEquals(defaultDir + "h2/", defaultVendorMigration.getResourcesDirectory());
    }

    private java.util.Properties getDefaultVendorProperties() {
        java.util.Properties properties = new java.util.Properties();
        String vendorFlywayLocation = MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/" + MigrationValues.VENDOR;
        properties.setProperty(PropertiesValues.FLYWAY_LOCATIONS, vendorFlywayLocation);
        properties.setProperty(PropertiesValues.JDBC_URL, PropertiesValues.H2_URL);
        properties.setProperty(PropertiesValues.DRIVER_CLASS, PropertiesValues.H2_DRIVER);
        return properties;
    }

    private Migration getMigration(Properties properties) {
        return new Migration(properties, baseDir, targetDir);
    }

    private Properties getBlankProperties() {
        return getProperties(new java.util.Properties());
    }

    private Properties getProperties(java.util.Properties properties) {
        return new Properties(properties);
    }
}