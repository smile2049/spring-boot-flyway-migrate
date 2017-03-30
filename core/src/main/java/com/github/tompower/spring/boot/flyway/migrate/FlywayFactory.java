package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import org.flywaydb.core.Flyway;

public class FlywayFactory {

    public static Flyway create(String directory, Properties properties) {
        Flyway flyway = new Flyway();
        flyway.setLocations(getLocations(directory));
//        flyway.setClassLoader(classLoader);
        flyway.setDataSource(properties.getUrl(), properties.getUser(), properties.getPass());
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

    private static String[] getLocations(String directory) {
        return new String[]{"filesystem:" + directory};
    }

}
