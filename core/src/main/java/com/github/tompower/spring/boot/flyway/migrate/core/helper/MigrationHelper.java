package com.github.tompower.spring.boot.flyway.migrate.core.helper;

public class MigrationHelper {

    public static String getMigrationFileName(int version, String description) {
        return MigrationValues.MIGRATION_FILE_PREFIX +
                String.valueOf(version) +
                MigrationValues.MIGRATION_FILE_SEPARATOR +
                getDescription(description) +
                MigrationValues.MIGRATION_FILE_SUFFIX;

    }

    private static String getDescription(String description) {
        return description != null ? description : MigrationValues.MIGRATION_FILE_DESCRIPTION;
    }
}
