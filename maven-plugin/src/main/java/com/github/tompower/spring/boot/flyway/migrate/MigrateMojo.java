package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Migrate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Run Flyway migrations
 */
@Mojo(name = "migrate")
public class MigrateMojo extends SpringBootFlywayMigrateMojo {

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new Migrate());
    }

}
