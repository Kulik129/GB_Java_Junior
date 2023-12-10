package org.example.lesson_3;

import java.io.Serializable;

public class SerializablePerson implements Serializable {
    private final String name;
    private int age;

    public SerializablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public SerializablePerson(String name) {
        this(name, 20);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SerializablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
