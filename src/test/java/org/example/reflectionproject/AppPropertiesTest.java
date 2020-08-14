package org.example.reflectionproject;

import org.example.reflectionproject.util.WorkWithProperties;
import org.example.reflectionproject.util.annotations.PropertyKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppPropertiesTest {

    @PropertyKey("userName")
    public String name;

    String lastName;

    @PropertyKey("id")
    public int id;

    @PropertyKey("userName")
    public String secondName = "Alex";

    AppPropertiesTest appPropertiesTest;
    WorkWithProperties workWithProperties;

    @Before
    public void createNewTestObjects() {
        appPropertiesTest = new AppPropertiesTest();
         workWithProperties = new WorkWithProperties();
        try {
            workWithProperties.initializerOfFields(appPropertiesTest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void methodShouldReturnCorrectAge() {

        Assert.assertEquals(245, appPropertiesTest.id);
    }

    @Test
    public void methodShouldReturnCorrectName() {

        Assert.assertEquals("John", appPropertiesTest.name);
    }

    @Test
    public void methodShouldReturnNull() {

        Assert.assertNull(appPropertiesTest.lastName);
    }

    @Test
    public void methodShouldReturnFalse() {

        Assert.assertNotEquals("Alex", appPropertiesTest.secondName);
    }
}
