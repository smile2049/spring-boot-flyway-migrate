package com.github.tompower.spring.boot.flyway.migrate.helper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    public static List<URL> getUrls(String dir) throws MalformedURLException {
        return Arrays.asList(new File(dir).toURI().toURL());
    }

    public static List<File> getFiles(String dir) {
        return FileUtils.listFiles(
                new File(dir),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        ).stream().collect(Collectors.toList());
    }
}
