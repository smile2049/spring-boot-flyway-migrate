package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Info;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "info")
public class InfoMojo extends SpringBootFlywayMigrateMojo {

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new Info());
    }

}
