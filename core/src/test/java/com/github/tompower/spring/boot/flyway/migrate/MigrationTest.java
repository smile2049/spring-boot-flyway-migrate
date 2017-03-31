package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesValues;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MigrationTest {

    private String baseDir = "/resourcesDir";
    private String defaultDir = baseDir + "/" + MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/";
    private String defaultFile = MigrationValues.MIGRATION_FILE_PREFIX + "1" + MigrationValues.MIGRATION_FILE_SUFFIX;

    @Test
    public void testFile() {
        Migration defaultMigration = getMigration(getProperties(new java.util.Properties()));
        assertEquals(defaultDir + defaultFile, defaultMigration.getFile(1).getAbsolutePath());
    }

    @Test
    public void testVendorFile() throws Exception {
        Migration defaultVendorMigration = getMigration(getProperties(getDefaultVendorProperties()));
        assertEquals(defaultDir + "h2/" + defaultFile, defaultVendorMigration.getFile(1).getAbsolutePath());
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
        return new Migration(properties, baseDir);
    }

    private Properties getProperties(java.util.Properties properties) {
        return new Properties(properties);
    }
}