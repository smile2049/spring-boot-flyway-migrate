package com.github.tompower.spring.boot.flyway.migrate;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginClassLoader {

    public static void updateClassLoader(List<URL> urls) throws PluginExecutionException {
        setClassLoader(getClassLoader(urls));
    }

    public static void setClassLoader(ClassLoader classLoader) {
        Thread.currentThread().setContextClassLoader(classLoader);
    }

    public static ClassLoader getClassLoader(List<URL> urls) throws PluginExecutionException {
        return URLClassLoader.newInstance(urls.toArray(new URL[0]), Thread.currentThread().getContextClassLoader());
    }

}
