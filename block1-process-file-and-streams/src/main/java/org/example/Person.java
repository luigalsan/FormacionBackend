package org.example;

public class Person {

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public int getAge() {
        return age;
    }



    private String name;
    private String town;
    private int age;
}
