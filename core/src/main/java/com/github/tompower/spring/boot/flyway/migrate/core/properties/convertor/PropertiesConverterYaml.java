package com.github.tompower.spring.boot.flyway.migrate.core.properties.convertor;

import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesConverterYaml implements PropertiesConverter {

    @Override
    public java.util.Properties convert(File propertiesFile) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(propertiesFile);
        updatePropertiesFromYaml(properties, inputStream);
        return properties;
    }

    private void updatePropertiesFromYaml(java.util.Properties properties, InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream);
        updateProperties(properties, (Map<String, Object>) new Yaml().load(reader), null);
    }

    private void updateProperties(java.util.Properties props, Map<String, Object> map, String path) {
        map.entrySet().stream().forEach((entry) -> {
            String key = entry.getKey();
            if (StringUtils.isNotEmpty(path)) {
                key = path + "." + key;
            }
            Object val = entry.getValue();
            if (val instanceof String) {
                props.put(key, val);
            } else if (val instanceof Map) {
                updateProperties(props, (Map<String, Object>) val, key);
            }
        });
    }
}
