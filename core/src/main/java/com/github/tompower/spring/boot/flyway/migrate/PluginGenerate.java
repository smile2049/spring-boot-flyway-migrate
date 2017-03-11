package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.messages.FlywayMigrateLogger;
import com.github.tompower.spring.boot.flyway.migrate.messages.Messages;
import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;

public class PluginGenerate {

    private final String resourceBaseDir;
    private final List<String> paths;
    private final String profile;
    private final FlywayMigrateLogger logger;

    public PluginGenerate(String resourceBaseDir, List<String> paths, String profile, FlywayMigrateLogger logger) {
        this.resourceBaseDir = resourceBaseDir;
        this.paths = paths;
        this.profile = profile;
        this.logger = logger;
    }

    private Resources resources;
    private Generate generate;
    private Writer writer;
    private Migration migration;

    public void generate() {
        try {
            init();
            List<String> updates = generate.getUpdates();
            if (!updates.isEmpty()) {
                File file = migration.getFile(generate.getCurrentVersion() + 1);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    if (!file.createNewFile()) {
                        logger.error(Messages.CANNOT_CREATE + file.getAbsolutePath());
                    }
                    writer.write(updates, file);
                    logger.info(Messages.CREATED + file.getAbsolutePath());
                } else {
                    logger.info(Messages.EXISTS + file.getAbsolutePath());
                }
            } else {
                logger.info(Messages.UP_TO_DATE);
            }
        } catch (SQLException | IOException | DependencyResolutionRequiredException | URISyntaxException ex) {
            logger.error(ex.getMessage());
        }
    }

    private void init() throws IOException, URISyntaxException, DependencyResolutionRequiredException {
        resources = Resources.getInstance(paths);
        Properties properties = resources.getProperties(profile);
        Hibernate hibernate = new HibernateFactory(properties, resources.getUrls()).create();
        generate = new Generate(hibernate);
        writer = new Writer();
        migration = new Migration(properties, resourceBaseDir);
    }

}
