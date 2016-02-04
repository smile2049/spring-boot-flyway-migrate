package com.github.tompower.spring.boot.flyway.migrate.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.yaml.snakeyaml.Yaml;

public class PropertiesProviderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private static PropertiesProvider propertiesProvider;

    @BeforeClass
    public static void setUp() throws Exception {
        propertiesProvider = new PropertiesProvider();
    }

    private final String PROPERTIES = PropertiesKeys.PROPERTIES;
    private final String YAML = PropertiesKeys.YAML;
    private final String BASE = "base";
    private final String DEV = "dev";

    @Test(expected = IOException.class)
    public void testNonExistingApplicationProperties() throws Exception {
        propertiesProvider.getProperties(getFiles(), null);
    }

    @Test
    public void testReadExistingApplicationProperties() throws Exception {
        setupProperties(null, PROPERTIES);
        setupProperties(DEV, PROPERTIES);
        Properties properties = propertiesProvider.getProperties(getFiles(), null);
        assertNotNull(properties);
        assertEquals(BASE, properties.getUrl());
    }

    @Test
    public void testReadExistingApplicationPropertiesWithProfile() throws Exception {
        setupProperties(null, PROPERTIES);
        setupProperties(DEV, PROPERTIES);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertNotNull(properties);
        assertEquals(DEV, properties.getUrl());
    }

    @Test
    public void testReadExistingApplicationYaml() throws Exception {
        setupProperties(null, YAML);
        Properties properties = propertiesProvider.getProperties(getFiles(), null);
        assertNotNull(properties);
        assertEquals(BASE, properties.getUrl());
    }

    @Test
    public void testReadExistingProfileApplicationYamlWithProfile() throws Exception {
        setupProperties(DEV, YAML);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertNotNull(properties);
        assertEquals(DEV, properties.getUrl());
    }

    private void setupProperties(String profile, String extension) throws IOException {
        File file = folder.newFile(getPath(profile, extension));
        switch (extension) {
            case PROPERTIES:
                writeProperties(file, profile);
                break;
            case YAML:
                writeYaml(file, profile);
                break;
            default:
                throw new AssertionError();
        }
    }

    private String getPath(String profile, String extension) {
        String thisProfile = profile != null ? "-" + profile : "";
        return PropertiesKeys.APPLICATION + thisProfile + "." + extension;
    }

    private List<File> getFiles() {
        return Arrays.asList(folder.getRoot().listFiles());
    }

    private void writeYaml(File file, String profile) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put(PropertiesKeys.JDBC_URL, getUrl(profile));
        new Yaml().dump(data, new FileWriter(file));
    }

    private void writeProperties(File file, String profile) throws IOException {
        java.util.Properties prop = new java.util.Properties();
        prop.setProperty(PropertiesKeys.JDBC_URL, getUrl(profile));
        prop.store(new FileOutputStream(file), null);
    }

    private String getUrl(String profile) {
        return profile != null ? profile : BASE;
    }

}
