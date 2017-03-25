package com.github.tompower.spring.boot.flyway.migrate;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.hibernate.dialect.Dialect;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;

public class Generate {

    private final Hibernate hibernate;
    private final Flyway flyway;

    public Generate(Hibernate hibernate, Flyway flyway) {
        this.hibernate = hibernate;
        this.flyway = flyway;
    }

    /**
     * Generate list of schema updates between Hibernate entities and configured database
     * @return List<String>
     */
    public List<String> getUpdates() {
        Dialect dialect = hibernate.getDialect();
        DatabaseMetadata databaseMetadata = hibernate.getDatabaseMetadata();
        List<SchemaUpdateScript> updates = hibernate.getConfiguration().generateSchemaUpdateScriptList(dialect, databaseMetadata);
        return updates.stream().map(SchemaUpdateScript::getScript).collect(Collectors.toList());
    }

    /**
     * Get current Flyway schema version
     * @return Integer
     * @throws SQLException
     */
    public Integer getCurrentVersion() throws SQLException {
        MigrationInfo current = flyway.info().current();
        if (current == null)
            return 0;
        return Integer.valueOf(current.getVersion().getVersion());
    }

}
