package com.github.tompower.spring.boot.flyway.migrate.internal;

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

    private static Properties properties;
    private static List<URL> urls;

    public static Hibernate create(Properties properties, List<URL> urls) {
        HibernateFactory.properties = properties;
        HibernateFactory.urls = urls;
        disableLogs();
        return new Hibernate(getEntityManagerFactory(getConfiguration()));
    }

    private static void disableLogs() throws SecurityException {
//        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
//        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
//        LogManager.getRootLogger().setLevel(Level.OFF);
    }

    private static EntityManagerFactory getEntityManagerFactory(Configuration configuration) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return new EntityManagerFactoryImpl(PersistenceUnitTransactionType.RESOURCE_LOCAL, true, null, configuration, serviceRegistry, null);
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        addDatasourceProperties(configuration);
        addEntities(configuration);
        return configuration;
    }

    private static void addDatasourceProperties(Configuration configuration) {
        configuration.setProperty(AvailableSettings.URL, properties.getUrl());
        configuration.setProperty(AvailableSettings.DRIVER, properties.getDriver());
        configuration.setProperty(AvailableSettings.USER, properties.getUser());
        configuration.setProperty(AvailableSettings.PASS, properties.getPass());
    }

    private static void addEntities(Configuration configuration) {
        final Reflections reflections = getReflections();
        reflections.getTypesAnnotatedWith(MappedSuperclass.class).stream().forEach(configuration::addAnnotatedClass);
        reflections.getTypesAnnotatedWith(Entity.class).stream().forEach(configuration::addAnnotatedClass);
    }

    private static Reflections getReflections() {
        Reflections.log = null;
        ConfigurationBuilder configuration = new ConfigurationBuilder().addUrls(urls);
        return new Reflections(configuration);
    }

}
