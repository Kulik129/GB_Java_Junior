package org.example.lesson_2;

public class Person {
    private final String name;
    private int age;

    private Person(String name) {
        this(name, 20);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", name, age);
    }
}
