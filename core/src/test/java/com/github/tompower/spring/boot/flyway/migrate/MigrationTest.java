package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesValues;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MigrationTest {

    private String baseDir = "/baseDir";
    private String defaultDir = baseDir + "/" + MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/";

    @Test
    public void testDefaultDirectory() throws Exception {
        Migration blankMigration = getMigration(getBlankProperties());
        assertEquals(defaultDir, blankMigration.getDirectory());
    }

    @Test
    public void testVendorDirectory() throws Exception {
        Migration defaultVendorMigration = getMigration(getProperties(getDefaultVendorProperties()));
        assertEquals(defaultDir + "h2/", defaultVendorMigration.getDirectory());
    }

    private java.util.Properties getDefaultVendorProperties() {
        java.util.Properties properties = new java.util.Properties();
        String vendorFlywayLocation = MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/" + MigrationValues.VENDOR;
        properties.setProperty(PropertiesValues.FLYWAY_LOCATIONS, vendorFlywayLocation);
        properties.setProperty(PropertiesValues.JDBC_URL, PropertiesValues.DEFAULT_URL);
        properties.setProperty(PropertiesValues.DRIVER_CLASS, PropertiesValues.DEFAULT_DRIVER);
        return properties;
    }

    private Migration getMigration(Properties properties) {
        return new Migration(properties, baseDir);
    }

    private Properties getBlankProperties() {
        return getProperties(new java.util.Properties());
    }

    private Properties getProperties(java.util.Properties properties) {
        return new Properties(properties);
    }
}