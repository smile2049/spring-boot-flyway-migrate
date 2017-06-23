package com.github.tompower.spring.boot.flyway.migrate.core.properties;

import com.github.tompower.spring.boot.flyway.migrate.core.MigrationValues;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.util.Arrays;

public class Properties extends java.util.Properties {

    public Properties() {
        super();
    }

    public Properties(java.util.Properties defaults) {
        super(defaults);
    }

    public String getUrl() {
        String url = this.getProperty(PropertiesValues.JDBC_URL);
        return url != null ? url : PropertiesValues.H2_URL;
    }

    public String getDriver() {
        String driver = this.getProperty(PropertiesValues.DRIVER_CLASS);
        return driver != null ? driver : PropertiesValues.H2_DRIVER;
    }

    public String getUser() {
        String username = this.getProperty(PropertiesValues.USERNAME);
        return username != null ? username : "";
    }

    public String getPass() {
        String password = this.getProperty(PropertiesValues.PASSWORD);
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
        String flywayLocations = this.getProperty(PropertiesValues.FLYWAY_LOCATIONS);
        return flywayLocations != null ? flywayLocations.split(",") : new String[]{""};
    }

    private String getVendor() {
        return DatabaseDriver.fromJdbcUrl(getUrl()).getId();
    }

}
