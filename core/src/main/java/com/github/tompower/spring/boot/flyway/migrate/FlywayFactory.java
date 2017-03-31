package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import org.flywaydb.core.Flyway;

public class FlywayFactory {

    public static Flyway create(Properties properties) {
        Flyway flyway = new Flyway();
        flyway.setLocations(properties.getFlywayLocations());
//        flyway.setClassLoader(classLoader);
        flyway.setDataSource(properties.getUrl(), properties.getUser(), properties.getPass());
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

}
