package org.example.reflectionproject.util;

import org.example.reflectionproject.util.annotations.PropertyKey;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class WorkWithProperties {

    public void initializerOfFields(Object object) throws IllegalAccessException {

        Class<?> objectClass = object.getClass();
        for(Field field : objectClass.getFields()) {
            if(field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                if(isPropertyPresent(propertyKey.value())) {
                    field.set(object, getProperty(propertyKey.value()));
                }
            }
        }
    }

    public Boolean isPropertyPresent(String key) {

        try(InputStream inputStream = new FileInputStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties.containsKey(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getProperty(String key) {

        try(InputStream inputStream = new FileInputStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String value = properties.getProperty(key);

            if(isNumber(value)) {
                return Integer.parseInt(value);
            } else {
                return value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isNumber(String value) {

        if(value == null || value.isEmpty()) {
            return false;
        }
        for(int i = 0; i < value.length(); i++) {
            if(!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
