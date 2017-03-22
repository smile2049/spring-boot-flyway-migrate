package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.net.MalformedURLException;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class Validator {

    private final String[] locations;
    private final ClassLoader classLoader;
    private final Properties properties;

    public Validator(String[] locations, ClassLoader classLoader, Properties properties) {
        this.locations = locations;
        this.classLoader = classLoader;
        this.properties = properties;
    }

    public void flywayValidate() throws FlywayException, MalformedURLException, DependencyResolutionRequiredException {
        Flyway flyway = new Flyway();
//        flyway.setLocations(locations);
        flyway.setClassLoader(classLoader);
        flyway.setDataSource(properties.getUrl(), properties.getUser(), properties.getPass());
        flyway.validate();
    }

}
