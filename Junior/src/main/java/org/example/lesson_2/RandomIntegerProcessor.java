package org.example.lesson_2;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerProcessor {
    // Найти все int поля с аннотацией RandomInteger и заполнить их рандомными значениями
    public static void processObject(Object obj) {
        Class<?> objClass = obj.getClass();
        for (Field declaredField : objClass.getDeclaredFields()) {
            if (int.class.isAssignableFrom(declaredField.getType()) && declaredField.isAnnotationPresent(RandomInteger.class)) {
                RandomInteger annotation = declaredField.getAnnotation(RandomInteger.class);
                int min = annotation.min();
                int max = annotation.max();

                int random = min + ThreadLocalRandom.current().nextInt(max - min);

                declaredField.setAccessible(true);
                try {
                    declaredField.set(obj, random);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex.getMessage(), ex);
                }
            }
        }
    }
}
