package com.github.tompower.spring.boot.flyway.migrate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public class Hibernate {

    private final EntityManagerFactory entityManagerFactory;

    public Hibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public DatabaseMetadata getDatabaseMetadata() {
        try {
            return new DatabaseMetadata(getConnection(), getDialect(), getConfiguration());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration getConfiguration() {
        return (Configuration) getFieldValue("configuration", getServiceRegistry());
    }

    public Dialect getDialect() {
        return (Dialect) getFieldValue("dialect", getSessionFactory());
    }

    public Connection getConnection() {
        try {
            return getServiceRegistry().getService(ConnectionProvider.class).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ServiceRegistry getServiceRegistry() {
        return (ServiceRegistry) getFieldValue("serviceRegistry", getSessionFactory());
    }

    private SessionFactory getSessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    private static Object getFieldValue(String fieldName, Object target) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(target);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
