package com.github.tompower.spring.boot.flyway.migrate.plugin;

import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;

public abstract class FlywayPlugin extends Plugin {

    @Override
    protected void init() throws PluginExecutionException {
        super.defaultInit();
    }
}
