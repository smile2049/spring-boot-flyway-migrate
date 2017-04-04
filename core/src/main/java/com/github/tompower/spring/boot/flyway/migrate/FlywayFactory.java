package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import org.flywaydb.core.Flyway;

public class FlywayFactory {

    public static Flyway create(Properties properties, ClassLoader classLoader) {
        Flyway flyway = new Flyway();
        flyway.setClassLoader(classLoader);
        flyway.setLocations(properties.getFlywayLocations());
        flyway.setDataSource(properties.getUrl(), properties.getUser(), properties.getPass());
        return flyway;
    }

}
