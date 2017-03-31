package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.MappedSuperclass;
import javax.persistence.spi.PersistenceUnitTransactionType;
import java.net.URL;
import java.util.List;

public class HibernateFactory {

    private final Properties properties;
    private final List<URL> urls;

    public HibernateFactory(Properties properties, List<URL> urls) {
        this.properties = properties;
        this.urls = urls;
    }

    public Hibernate create() {
        disableLogs();
        return new Hibernate(getEntityManagerFactory(getConfiguration(properties)));
    }

    private void disableLogs() throws SecurityException {
//        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
//        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
//        LogManager.getRootLogger().setLevel(Level.OFF);
    }

    private EntityManagerFactory getEntityManagerFactory(Configuration configuration) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return new EntityManagerFactoryImpl(PersistenceUnitTransactionType.RESOURCE_LOCAL, true, null, configuration, serviceRegistry, null);
    }

    private Configuration getConfiguration(Properties properties) {
        Configuration configuration = new Configuration();
        addDatasourceProperties(configuration, properties);
        addEntities(configuration);
        return configuration;
    }

    private void addDatasourceProperties(Configuration configuration, Properties properties) {
        configuration.setProperty(AvailableSettings.URL, properties.getUrl());
        configuration.setProperty(AvailableSettings.DRIVER, properties.getDriver());
        configuration.setProperty(AvailableSettings.USER, properties.getUser());
        configuration.setProperty(AvailableSettings.PASS, properties.getPass());
    }

    private void addEntities(Configuration configuration) {
        final Reflections reflections = getReflections();
        reflections.getTypesAnnotatedWith(MappedSuperclass.class).stream().forEach(configuration::addAnnotatedClass);
        reflections.getTypesAnnotatedWith(Entity.class).stream().forEach(configuration::addAnnotatedClass);
    }

    private Reflections getReflections() {
        Reflections.log = null;
        ConfigurationBuilder configuration = new ConfigurationBuilder().addUrls(urls);
        return new Reflections(configuration);
    }

}
