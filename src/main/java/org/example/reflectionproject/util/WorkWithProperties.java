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
                    if(field.getType() == int.class) {
                        field.setInt(object, Integer.parseInt(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == double.class) {
                        field.setDouble(object, Double.parseDouble(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == boolean.class) {
                        field.setBoolean(object, Boolean.parseBoolean(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == byte.class) {
                        field.setByte(object, Byte.parseByte(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == short.class) {
                        field.setShort(object, Short.parseShort(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == float.class) {
                        field.setFloat(object, Float.parseFloat(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == long.class) {
                        field.setLong(object, Long.parseLong(getProperty(propertyKey.value())));
                    }
                    if(field.getType() == String.class) {
                        field.set(object, getProperty(propertyKey.value()));
                    }
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


    public static String getProperty(String key) {

        String stringValue = "";

        try(InputStream inputStream = new FileInputStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            stringValue = properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringValue;
    }
}
