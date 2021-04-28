package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);
    private final Properties properties = new Properties();

    private PropertyReader() {
    }

    private static class PropertyReaderHolder {
        private final static PropertyReader instance = new PropertyReader();
    }

    public static PropertyReader getInstance() {
        return PropertyReaderHolder.instance;
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */

    public ApplicationProperties readProperties() {
        final String propertiesFileName = "." + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "application.properties";

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(propertiesFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return new ApplicationProperties(
                properties.getProperty("inputRootDir"),
                properties.getProperty("outputRootDir"),
                properties.getProperty("crewFileName"),
                properties.getProperty("missionsFileName"),
                properties.getProperty("spaceshipsFileName"),
                properties.getProperty("spacemapFileName"),
                Integer.parseInt(properties.getProperty("fileRefreshRate")),
                properties.getProperty("dateTimeFormat")
        );
    }

}