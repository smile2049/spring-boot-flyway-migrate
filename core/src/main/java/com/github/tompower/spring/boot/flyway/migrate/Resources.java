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

    private final List<String> resources;
    private static Resources instance = null;

    protected Resources(List<String> resources) throws PluginExecutionException {
        this.resources = resources;
        updateClassloader();
    }

    private void updateClassloader() throws PluginExecutionException {
        Thread.currentThread().setContextClassLoader(getClassloader());
    }

    public static Resources getInstance(List<String> elements) throws PluginExecutionException {
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
    public ClassLoader getClassloader() throws PluginExecutionException {
        if (classLoader == null) {
            try {
                return classLoader = URLClassLoader.newInstance(getUrls().toArray(new URL[0]), Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException e) {
                throw new PluginExecutionException(e.getMessage());
            }
        }
        return classLoader;
    }

    public List<URL> getUrls() throws MalformedURLException {
        List<URL> urls = new ArrayList<>();
        for (String resource : resources) {
            urls.add(new File(resource).toURI().toURL());
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
    public Properties getProperties(String profile) throws PluginExecutionException, URISyntaxException, IOException {
        return new PropertiesProvider().getProperties(getResourceFiles(), profile);
    }

    private List<File> getResourceFiles() throws PluginExecutionException, URISyntaxException {
        File[] files = new File(getClassloader().getResource("").toURI()).listFiles();
        return Arrays.asList(files);
    }

}
