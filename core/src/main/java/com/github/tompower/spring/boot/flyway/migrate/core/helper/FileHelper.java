package com.github.tompower.spring.boot.flyway.migrate.core.helper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<File> getFiles(List<String> paths) {
        return paths.stream()
                .flatMap(path -> getFiles(path).stream())
                .collect(Collectors.toList());
    }

    private static List<File> getFiles(String path) {
        Collection<File> files = FileUtils.listFiles(
                new File(path),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        );
        return files.stream().collect(Collectors.toList());
    }

    public static List<URL> getUrls(List<String> paths) {
        return paths.stream()
                .map(path -> getUrl(new File(path)))
                .collect(Collectors.toList());
    }

    private static URL getUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
