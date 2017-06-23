package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.plugin.Generate;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Generate Flyway migrations
 */
@Mojo(name = "generate")
public class GenerateMojo extends SpringBootFlywayMigrateMojo {

    @Override
    public void execute() throws MojoExecutionException {
        super.execute(new Generate());
    }

}
