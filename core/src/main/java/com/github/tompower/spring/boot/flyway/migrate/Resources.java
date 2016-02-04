package com.github.tompower.spring.boot.flyway.migrate;

import com.github.tompower.spring.boot.flyway.migrate.properties.Properties;
import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.maven.artifact.DependencyResolutionRequiredException;

public class Resources {

    private final List<String> paths;
    private static Resources instance = null;

    protected Resources(List<String> elements) {
        this.paths = elements;
        updateClassloader();
    }

    private void updateClassloader() {
        try {
            Thread.currentThread().setContextClassLoader(getClassloader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (DependencyResolutionRequiredException e) {
            e.printStackTrace();
        }
    }

    public static Resources getInstance(List<String> elements) {
        if (instance == null) {
            instance = new Resources(elements);
        }
        return instance;
    }

    private ClassLoader classLoader;

    /**
     * Get project classloader
     *
     * @return ClassLoader
     * @throws MalformedURLException
     * @throws DependencyResolutionRequiredException
     */
    public ClassLoader getClassloader() throws MalformedURLException, DependencyResolutionRequiredException {
        if (classLoader == null) {
            classLoader = URLClassLoader.newInstance(getUrls().toArray(new URL[0]), Thread.currentThread().getContextClassLoader());
        }
        return classLoader;
    }

    public List<URL> getUrls() throws MalformedURLException {
        List<URL> urls = new ArrayList<>();
        for (String path : paths) {
            urls.add(new File(path).toURI().toURL());
        }
        return urls;
    }

    /**
     * Get com.github.spring.boot.flyway.migrate.utils.properites.Properties for project
     *
     * @param profile
     * @return Properties
     * @throws IOException
     * @throws URISyntaxException
     * @throws org.apache.maven.artifact.DependencyResolutionRequiredException
     */
    public Properties getProperties(String profile) throws IOException, URISyntaxException, DependencyResolutionRequiredException {
        return new PropertiesProvider().getProperties(getResourceFiles(), profile);
    }

    private List<File> getResourceFiles() throws DependencyResolutionRequiredException, MalformedURLException, URISyntaxException {
        File[] files = new File(getClassloader().getResource("").toURI()).listFiles();
        return Arrays.asList(files);
    }

    private final String MIGRATION_FILE_BASE = "src/main/resources/db/migration/V";
    private final String MIGRATION_FILE_SUFFIX = "__migration.sql";

    /**
     * Get output file for Flyway Migration
     *
     * @param version
     * @return File
     */
    public File getFlywayOutputFile(Integer version) {
        return new File(MIGRATION_FILE_BASE + String.valueOf(version) + MIGRATION_FILE_SUFFIX);
    }

}
