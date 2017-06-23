package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.core.Migration;
import com.github.tompower.spring.boot.flyway.migrate.core.helper.MigrationHelper;
import com.github.tompower.spring.boot.flyway.migrate.core.helper.MigrationValues;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.PropertiesValues;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MigrationTest {

    private String baseDir = "/resourcesDir";
    private String defaultDir = baseDir + "/" + MigrationValues.DEFAULT_MIGRATION_DIRECTORY + "/";
    private String defaultFile = MigrationHelper.getMigrationFileName(1, null);

    @Test
    public void testFile() {
        Migration defaultMigration = getMigration(getProperties(new java.util.Properties()));
        assertEquals(defaultDir + defaultFile, defaultMigration.getFile(1, null).getAbsolutePath());
    }

    @Test
    public void testVendorFile() throws Exception {
        Migration defaultVendorMigration = getMigration(getProperties(getDefaultVendorProperties()));
        assertEquals(defaultDir + "h2/" + defaultFile, defaultVendorMigration.getFile(1, null).getAbsolutePath());
    }

    private String description = "description";
    private String descriptionFile = MigrationHelper.getMigrationFileName(1, description);

    @Test
    public void testDescriptionFile() throws Exception {
        Properties properties = getProperties(new java.util.Properties());
        properties.putAll(getDescriptionProperties());
        Migration descriptionMigration = getMigration(properties);
        assertEquals(defaultDir + descriptionFile, descriptionMigration.getFile(1, description).getAbsolutePath());
    }

    private Properties getDescriptionProperties() {
        Properties descriptionProperties = new Properties();
        descriptionProperties.put(description, description);
        return descriptionProperties;
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