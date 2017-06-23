package com.github.tompower.spring.boot.flyway.migrate.plugin.abs;

import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;

public abstract class PluginBase extends Plugin {

    @Override
    protected void init() throws PluginExecutionException {
        super.defaultInit();
    }
}
