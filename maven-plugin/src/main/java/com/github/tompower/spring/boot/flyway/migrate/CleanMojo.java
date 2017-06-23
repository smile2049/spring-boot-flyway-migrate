package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Clean;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "clean")
public class CleanMojo extends SpringBootFlywayMigrateMojo {

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new Clean());
    }

}
