package com.github.tompower.spring.boot.flyway.migrate.properties.convertor;

import com.github.tompower.spring.boot.flyway.migrate.properties.PropertiesKeys;

public class PropertiesConverterFactory {

    private final String type;

    public PropertiesConverterFactory(String type) {
        this.type = type;
    }

    public PropertiesConverter getPropertiesConverter() {
        switch (type) {
            case PropertiesKeys.PROPERTIES:
                return new PropertiesConverterProperties();
            case PropertiesKeys.YAML:
                return new PropertiesConverterYaml();
            default:
                throw new AssertionError();
        }
    }

}
