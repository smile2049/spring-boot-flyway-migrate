package com.github.tompower.spring.boot.flyway.migrate;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginClassloader {

    public static void updateClassloader(List<URL> urls) throws PluginExecutionException {
        Thread.currentThread().setContextClassLoader(getClassloader(urls));
    }

    private static ClassLoader classLoader;

    /**
     * Get project classloader
     *
     * @return ClassLoader
     * @throws MalformedURLException
     */
    public static ClassLoader getClassloader(List<URL> urls) throws PluginExecutionException {
        if (classLoader == null) {
            return classLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]), Thread.currentThread().getContextClassLoader());
        }
        return classLoader;
    }

}
