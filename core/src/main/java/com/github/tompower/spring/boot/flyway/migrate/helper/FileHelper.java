package com.github.tompower.spring.boot.flyway.migrate.helper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<File> getFiles(List<String> dirs) {
        List<File> files = new ArrayList<>();
        for (String dir : dirs) {
            files.addAll(getFiles(dir));
        }
        return files;
    }

    public static List<File> getFiles(String dir) {
        return FileUtils.listFiles(
                new File(dir),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        ).stream().collect(Collectors.toList());
    }

    public static List<URL> getUrls(List<String> dirs) {
        return dirs.stream().map(d -> getUrl(new File(d))).collect(Collectors.toList());
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
