package com.github.tompower.spring.boot.flyway.migrate.properties.convertor;

import java.io.File;
import java.io.IOException;

public interface PropertiesConverter {

    java.util.Properties convert(File propertiesFile) throws IOException;
}
