package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.helper.FileHelper;
import com.github.tompower.spring.boot.flyway.migrate.helper.Messages;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PluginGenerate extends Plugin {

    @Override
    public void execute() throws PluginExecutionException {
        try {
            List<String> updates = generate.getUpdates();
            if (!updates.isEmpty()) {
                File file = migration.getFile(generate.getCurrentVersion() + 1);
                if (!file.exists()) {
                    if (!file.getParentFile().mkdirs() || !file.createNewFile()) {
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

    private Generate generate;
    private Writer writer;
    private Migration migration;

    @Override
    protected void init() throws PluginExecutionException {
        super.defaultInit();
        generate = new Generate(new HibernateFactory(properties, FileHelper.getUrls(buildDirs)).create(), flyway);
        writer = new Writer();
        migration = new Migration(properties, resourcesDir);
    }

}
