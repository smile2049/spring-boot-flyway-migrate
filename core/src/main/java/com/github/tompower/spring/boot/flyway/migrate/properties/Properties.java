package com.github.tompower.spring.boot.flyway.migrate.properties;

public class Properties {

    final java.util.Properties properties;

    public Properties(java.util.Properties properties) {
        this.properties = properties;
    }

    public String getUrl() {
        String url = properties.getProperty(PropertiesValues.JDBC_URL);
        return url != null ? url : PropertiesValues.DEFAULT_URL;
    }

    public String getDriver() {
        String driver = properties.getProperty(PropertiesValues.DRIVER_CLASS);
        return driver != null ? driver : PropertiesValues.DEFAULT_DRIVER;
    }

    public String getUser() {
        String username = properties.getProperty(PropertiesValues.USERNAME);
        return username != null ? username : "";
    }

    public String getPass() {
        String password = properties.getProperty(PropertiesValues.PASSWORD);
        return password != null ? password : "";
    }

    public String getFlywayLocations() {
        String flywayLocations = properties.getProperty(PropertiesValues.FLYWAY_LOCATIONS);
        return flywayLocations != null ? flywayLocations : "";
    }

}
