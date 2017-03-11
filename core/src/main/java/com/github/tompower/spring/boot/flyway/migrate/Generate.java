package com.github.tompower.spring.boot.flyway.migrate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;

public class Generate {

    private final Hibernate hibernate;

    public Generate(Hibernate hibernate) {
        this.hibernate = hibernate;
    }

    /**
     * Generate list of schema updates between Hibernate entities and configured database
     * @return List<String>
     */
    public List<String> getUpdates() {
        List<SchemaUpdateScript> generateSchemaUpdateScriptList = hibernate.getConfiguration().generateSchemaUpdateScriptList(hibernate.getDialect(), hibernate.getDatabaseMetadata());
        return generateSchemaUpdateScriptList.stream().map(SchemaUpdateScript::getScript).collect(Collectors.toList());
    }

    /**
     * Get current Flyway schema version
     * @return Integer
     * @throws SQLException
     */
    public Integer getCurrentVersion() throws SQLException {
        ResultSet tables = hibernate.getConnection().getMetaData().getTables(null, null, "schema_version", new String[]{"TABLE"});
        if (tables.next()) {
            String sql = "select max(s.version) as max_version from schema_version s";
            ResultSet rs = hibernate.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                return (int) rs.getDouble("max_version");
            }
        }
        return 0;
    }

}
