package com.github.tompower.spring.boot.flyway.migrate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class Resources {

    private final String targetDir;

    public Resources(String targetDir) throws PluginExecutionException {
        this.targetDir = targetDir;
    }

    public List<URL> getUrls() throws MalformedURLException {
        return Arrays.asList(new File(targetDir).toURI().toURL());
    }

    public List<File> getFiles() {
        return FileUtils.listFiles(
                new File(targetDir),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        ).stream().collect(Collectors.toList());
    }

}
