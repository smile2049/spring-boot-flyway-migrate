package com.github.tompower.spring.boot.flyway.migrate.core.properties.convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConverterProperties implements PropertiesConverter {

    @Override
    public java.util.Properties convert(File propertiesFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFile));
        return properties;
    }
}
