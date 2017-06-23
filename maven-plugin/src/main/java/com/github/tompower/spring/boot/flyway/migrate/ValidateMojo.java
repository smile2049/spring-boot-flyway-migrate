package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Validate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Run Flyway migrations
 */
@Mojo(name = "validate")
public class ValidateMojo extends SpringBootFlywayMigrateMojo {

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new Validate());
    }

}
