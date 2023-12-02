package org.example.lesson_2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person dmitrii = constructor.newInstance("Dmitrii");
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(dmitrii, "Dmitriiiiiiiiii");
        System.out.println(dmitrii);
    }
}
