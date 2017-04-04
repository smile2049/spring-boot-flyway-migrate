package com.github.tompower.spring.boot.flyway.migrate.internal;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.hibernate.dialect.Dialect;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Generate {

    private final Hibernate hibernate;
    private final Flyway flyway;

    public Generate(Hibernate hibernate, Flyway flyway) {
        this.hibernate = hibernate;
        this.flyway = flyway;
    }

    /**
     * Generate list of schema updates between Hibernate entities and configured database
     *
     * @return List<String>
     */
    public List<String> getUpdates() {
        return getSchemaUpdateScripts().stream()
                .map(SchemaUpdateScript::getScript)
                .collect(Collectors.toList());
    }

    private List<SchemaUpdateScript> getSchemaUpdateScripts() {
        Dialect dialect = hibernate.getDialect();
        DatabaseMetadata databaseMetadata = hibernate.getDatabaseMetadata();
        return hibernate.getConfiguration().generateSchemaUpdateScriptList(dialect, databaseMetadata);
    }

    /**
     * Get current Flyway schema version
     *
     * @return Integer
     * @throws SQLException
     */
    public Integer getCurrentVersion() throws SQLException {
        MigrationInfo current = flyway.info().current();
        return current != null ? Integer.valueOf(current.getVersion().getVersion()) : 0;
    }

}
