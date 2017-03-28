package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class PluginGenerate extends Plugin {

    private Generate generate;
    private Writer writer;

    @Override
    public void execute() throws PluginExecutionException {
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
        } catch (IOException | SQLException e) {
            logger.error(e.getMessage());
            throw new PluginExecutionException(e.getMessage());
        }

    }

    @Override
    protected void init() throws PluginExecutionException {
        try {
            super.init();
            Hibernate hibernate = new HibernateFactory(properties, resources.getUrls()).create();
            generate = new Generate(hibernate, flyway);
            writer = new Writer();
        } catch (MalformedURLException e) {
            throw new PluginExecutionException(e.getMessage());
        }
    }

}
