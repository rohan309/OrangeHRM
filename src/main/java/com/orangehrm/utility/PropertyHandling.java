package com.orangehrm.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {

    String filePath = "src/test/resources/config.properties";
    FileInputStream inputStream;
    Properties properties;

    public PropertyHandling() {
        try {
            inputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPropertyValue(String key) {

        String text=properties.getProperty(key);
        System.out.println(text);
        return text;
    }


}
