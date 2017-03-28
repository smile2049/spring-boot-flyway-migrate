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

    private final String PROPERTIES = PropertiesValues.PROPERTIES;
    private final String YAML = PropertiesValues.YAML;
    private final String BASE = "base";
    private final String DEV = "dev";

    @Test(expected = IOException.class)
    public void testNonExistingApplicationProperties() throws Exception {
        propertiesProvider.getProperties(null, null);
    }

    @Test
    public void testReadExistingApplicationProperties() throws Exception {
        setupProperties(null, PROPERTIES);
        setupProperties(DEV, PROPERTIES);
        Properties properties = propertiesProvider.getProperties(getFiles(), null);
        assertPropertiesOk(properties, DEV);
    }

    @Test
    public void testReadExistingApplicationPropertiesWithProfile() throws Exception {
        setupProperties(null, PROPERTIES);
        setupProperties(DEV, PROPERTIES);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertPropertiesOk(properties, DEV);
    }

    @Test
    public void testReadExistingApplicationYaml() throws Exception {
        setupProperties(null, YAML);
        Properties properties = propertiesProvider.getProperties(getFiles(), null);
        assertPropertiesOk(properties, DEV);
    }

    @Test
    public void testReadExistingProfileApplicationYamlWithProfile() throws Exception {
        setupProperties(DEV, YAML);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertPropertiesOk(properties, DEV);
    }


    private void assertPropertiesOk(Properties properties, String profile) {
        assertNotNull(properties);
        assertEquals(getUrl(profile), properties.getUrl());
    }

    @Test
    public void testReadProfileApplicationPropertiesWithBlankEntry() throws Exception {
        makeFile(DEV, PROPERTIES);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertNotNull(properties);
        assertEquals(PropertiesValues.H2_URL, properties.getUrl());
    }
    @Test
    public void testReadProfileApplicationYamlWithBlankEntry() throws Exception {
        makeFile(DEV, YAML);
        Properties properties = propertiesProvider.getProperties(getFiles(), DEV);
        assertNotNull(properties);
        assertEquals(PropertiesValues.H2_URL, properties.getUrl());
    }

    private void setupProperties(String profile, String extension) throws IOException {
        File file = makeFile(profile, extension);
        writeToFile(profile, extension, file);
    }

    private void writeToFile(String profile, String extension, File file) throws IOException {
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

    private File makeFile(String profile, String extension) throws IOException {
        return folder.newFile(getPath(profile, extension));
    }

    private String getPath(String profile, String extension) {
        String thisProfile = profile != null ? "-" + profile : "";
        return PropertiesValues.APPLICATION + thisProfile + "." + extension;
    }

    private List<File> getFiles() {
        return Arrays.asList(folder.getRoot().listFiles());
    }

    private void writeYaml(File file, String profile) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put(PropertiesValues.JDBC_URL, getUrl(profile));
        new Yaml().dump(data, new FileWriter(file));
    }

    private void writeProperties(File file, String profile) throws IOException {
        java.util.Properties prop = new java.util.Properties();
        prop.setProperty(PropertiesValues.JDBC_URL, getUrl(profile));
        prop.store(new FileOutputStream(file), null);
    }

    private String getUrl(String profile) {
        return profile != null ? profile : BASE;
    }

}
