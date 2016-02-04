package com.github.tompower.spring.boot.flyway.migrate.properties;

public class Properties {

    final java.util.Properties properties;

    public Properties(java.util.Properties properties) {
        this.properties = properties;
    }

    public String getUrl() {
        return properties.getProperty(PropertiesKeys.JDBC_URL);
    }

    public String getDriver() {
        return properties.getProperty(PropertiesKeys.DRIVER_CLASS);
    }

    public String getUser() {
        return properties.getProperty(PropertiesKeys.USERNAME);
    }

    public String getPass() {
        return properties.getProperty(PropertiesKeys.PASSWORD);
    }

}
