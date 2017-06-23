package com.github.tompower.spring.boot.flyway.migrate.plugin;

import com.github.tompower.spring.boot.flyway.migrate.plugin.abs.PluginBase;
import com.github.tompower.spring.boot.flyway.migrate.plugin.helper.PluginExecutionException;

public class Validate extends PluginBase {

    @Override
    public void execute() throws PluginExecutionException {
        flyway.validate();
    }

}
