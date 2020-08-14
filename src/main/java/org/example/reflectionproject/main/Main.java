package org.example.reflectionproject.main;

import org.example.reflectionproject.util.AppProperties;
import org.example.reflectionproject.util.WorkWithProperties;

public class Main {
    public static void main(String[] args) {
        AppProperties appProperties = new AppProperties();
        WorkWithProperties workWithProperties = new WorkWithProperties();
        try {
            workWithProperties.initializerOfFields(appProperties);
            System.out.println("Id : " + appProperties.id + "\nName : " + appProperties.name + "\nSalary : " + appProperties.salary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
