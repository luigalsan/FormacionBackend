package org.example;

public class Person {

    public Person(String name, String town, Integer age) {
        this.name = name;
        this.town = town;
        this.age = age; //autoboxing para manejar nulos de entrada y convertirlos a 0 tal y como indica el ejercicio
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
