package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class Migrator {

    public static int flywayMigrate(String[] locations, ClassLoader classLoader, Properties properties) throws FlywayException {
        Flyway flyway = new Flyway();
        flyway.setLocations(locations);
        flyway.setClassLoader(classLoader);
        flyway.setDataSource(properties.getUrl(), properties.getUser(), properties.getPass());
        flyway.setBaselineOnMigrate(true);
        return flyway.migrate();
    }

}
