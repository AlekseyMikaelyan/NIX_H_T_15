package org.example.reflectionproject;

import org.example.reflectionproject.util.annotations.PropertyKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class AppPropertiesTest {

    @PropertyKey("name")
    public String name;

    String lastName;

    @PropertyKey("age")
    public int age;

    AppPropertiesTest appPropertiesTest;
    WorkWithPropertiesTest workWithPropertiesTest;

    static class WorkWithPropertiesTest {

        public void initializerOfFields(Object object) throws IllegalAccessException {

            Class<?> objectClass = object.getClass();
            for (Field field : objectClass.getFields()) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                    if (isPropertyPresent(propertyKey.value())) {
                        field.set(object, getProperty(propertyKey.value()));
                    }
                }
            }
        }

        public Boolean isPropertyPresent(String key) {

            try (InputStream is = new FileInputStream("test.properties")) {
                Properties properties = new Properties();
                properties.load(is);

                return properties.containsKey(key);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public Object getProperty(String key) {

            try (InputStream is = new FileInputStream("test.properties")) {
                Properties properties = new Properties();
                properties.load(is);

                String value = properties.getProperty(key);

                if (isNumber(value)) {
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

            if (value == null || value.isEmpty()) {
                return false;
            }
            for (int i = 0; i < value.length(); i++) {
                if (!Character.isDigit(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    @Before
    public void createNewTestObjects() {
        appPropertiesTest = new AppPropertiesTest();
        workWithPropertiesTest = new WorkWithPropertiesTest();
        try {
            workWithPropertiesTest.initializerOfFields(appPropertiesTest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void methodShouldReturnCorrectAge() {

        Assert.assertEquals(30, appPropertiesTest.age);
    }

    @Test
    public void methodShouldReturnCorrectName() {

        Assert.assertEquals("Alex", appPropertiesTest.name);
    }

    @Test
    public void methodShouldReturnNull() {
        Assert.assertNull(appPropertiesTest.lastName);
    }
}
