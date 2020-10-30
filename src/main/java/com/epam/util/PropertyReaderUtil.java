package com.epam.util;

import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    public static void loadProperties() {
        final String propertiesFileName = "resource/application.properties";
        /**
         * try-with-resource using FileInputStream
         * @see https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example
         * as a result - you should populate {@link com.epam.domain.ApplicationProperties} with corresponding
         * values from property file
         */
    }
}
