package com.github.tompower.spring.boot.flyway.migrate.properties;

public class PropertiesValues {
    public static final String DRIVER_CLASS = "spring.datasource.driverClassName";
    public static final String JDBC_URL = "spring.datasource.url";
    public static final String USERNAME = "spring.datasource.username";
    public static final String PASSWORD = "spring.datasource.password";
    public static final String FLYWAY_LOCATIONS = "flyway.locations";

    public static final String PROPERTIES = "properties";
    public static final String YAML = "yaml";
    public static final String APPLICATION = "application";

    public static final String H2_URL = "jdbc:h2:./tmp/test_db_h2;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;";
    public static final String H2_DRIVER = "org.h2.Driver";
}
