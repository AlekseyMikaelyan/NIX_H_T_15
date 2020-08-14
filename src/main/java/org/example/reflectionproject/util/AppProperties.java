package org.example.reflectionproject.util;

import org.example.reflectionproject.util.annotations.PropertyKey;

public class AppProperties {

    @PropertyKey("id")
    public int id;

    @PropertyKey("userName")
    public String name;

    @PropertyKey("salary")
    public double salary;

    public String lastName;

    public long bankAccount;

}
