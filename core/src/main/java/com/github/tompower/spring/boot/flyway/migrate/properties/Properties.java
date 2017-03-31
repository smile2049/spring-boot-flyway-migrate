package com.github.tompower.spring.boot.flyway.migrate.properties;

import com.github.tompower.spring.boot.flyway.migrate.MigrationValues;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.util.Arrays;

public class Properties {

    final java.util.Properties properties;

    public Properties(java.util.Properties properties) {
        this.properties = properties;
    }

    public String getUrl() {
        String url = properties.getProperty(PropertiesValues.JDBC_URL);
        return url != null ? url : PropertiesValues.H2_URL;
    }

    public String getDriver() {
        String driver = properties.getProperty(PropertiesValues.DRIVER_CLASS);
        return driver != null ? driver : PropertiesValues.H2_DRIVER;
    }

    public String getUser() {
        String username = properties.getProperty(PropertiesValues.USERNAME);
        return username != null ? username : "";
    }

    public String getPass() {
        String password = properties.getProperty(PropertiesValues.PASSWORD);
        return password != null ? password : "";
    }

    /**
     * Get flyway locations including {vendor} directory updates
     *
     * @return
     */
    public String[] getFlywayLocations() {
        return Arrays.stream(getPropertiesFlywayLocations())
                .map(fl -> fl.replace(MigrationValues.VENDOR, getVendor()))
                .toArray(String[]::new);
    }

    private String[] getPropertiesFlywayLocations() {
        String flywayLocations = properties.getProperty(PropertiesValues.FLYWAY_LOCATIONS);
        return flywayLocations != null ? flywayLocations.split(",") : new String[]{""};
    }

    private String getVendor() {
        return DatabaseDriver.fromJdbcUrl(getUrl()).getId();
    }

}
