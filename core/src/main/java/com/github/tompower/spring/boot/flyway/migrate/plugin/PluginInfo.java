package com.github.tompower.spring.boot.flyway.migrate.plugin;

import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;

public class PluginInfo extends FlywayPlugin {

    @Override
    public void execute() throws PluginExecutionException {
        flyway.info();
    }

}
