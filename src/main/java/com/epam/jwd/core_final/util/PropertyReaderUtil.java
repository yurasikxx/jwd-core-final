package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "application.properties";

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(propertiesFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readProperties() {
        ApplicationProperties applicationProperties = new ApplicationProperties(
                properties.getProperty("inputRootDir"),
                properties.getProperty("outputRootDir"),
                properties.getProperty("crewFileName"),
                properties.getProperty("missionsFileName"),
                properties.getProperty("spaceshipsFileName"),
                Integer.parseInt(properties.getProperty("fileRefreshRate")),
                properties.getProperty("dateTimeFormat")
        );
    }

}