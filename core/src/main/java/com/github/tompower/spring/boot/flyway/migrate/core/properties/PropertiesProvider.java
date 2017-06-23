package com.github.tompower.spring.boot.flyway.migrate.core.properties;

import com.github.tompower.spring.boot.flyway.migrate.core.properties.convertor.PropertiesConverter;
import com.github.tompower.spring.boot.flyway.migrate.core.properties.convertor.PropertiesConverterFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Locate and return project properties
 */
public class PropertiesProvider {

    /**
     * Get Spring Boot application-[<profile>].properties|yaml files as
     * com.github.spring.boot.flyway.migrate.utils.properites.Properties, match on profile if provided
     *
     * @param profile
     * @param files
     * @return Properties
     * @throws IOException
     */
    public static Properties getProperties(List<File> files, String profile) throws IOException {
        File propertiesFile = PropertiesFinder.find(files, profile);
        PropertiesConverter propertiesConverter = new PropertiesConverterFactory(PropertiesUtils.getExtension(propertiesFile)).getPropertiesConverter();
        return new Properties(propertiesConverter.convert(propertiesFile));
    }

    private static class PropertiesFinder {

        /**
         * Find properties file from paths, match on profile if provided
         *
         * @param files
         * @param profile
         * @return
         * @throws IOException
         */
        public static File find(List<File> files, String profile) throws IOException {
            Optional<File> propertiesFile = files.stream()
                    .filter(file -> isApplication(PropertiesUtils.getName(file))
                            && isProperties(PropertiesUtils.getExtension(file))
                            && matchesProfile(PropertiesUtils.getProfile(file), profile))
                    .findFirst();
            if (!propertiesFile.isPresent()) {
                throw new IOException("Properties file not found");
            }
            return propertiesFile.get();
        }

        private static boolean matchesProfile(String profile1, String profile2) {
            return StringUtils.isEmpty(profile1) && StringUtils.isEmpty(profile2)
                    || !StringUtils.isEmpty(profile1) && profile1.equals(profile2);
        }

        private static boolean isApplication(String path) {
            return !StringUtils.isEmpty(path)
                    && path.contains(PropertiesValues.APPLICATION);
        }

        private static boolean isProperties(String extension) {
            return !StringUtils.isEmpty(extension)
                    && extension.equals(PropertiesValues.PROPERTIES) || extension.equals(PropertiesValues.YAML);
        }

    }

    private static class PropertiesUtils {

        public static String getName(File file) {
            return FilenameUtils.getBaseName(file.getAbsolutePath());
        }

        public static String getExtension(File file) {
            return FilenameUtils.getExtension(file.getAbsolutePath());
        }

        public static String getProfile(File file) {
            return StringUtils.substringAfter(FilenameUtils.getBaseName(file.getAbsolutePath()), "-");
        }

    }

}
