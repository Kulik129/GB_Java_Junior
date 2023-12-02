package org.example.lesson_2;

import java.lang.reflect.Field;

public class Annotation {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        System.out.println(myClass.getNegative()); // 0
        System.out.println(myClass.getPositive()); // 0

        RandomIntegerProcessor.processObject(myClass);

        System.out.println(myClass.getNegative()); // <0
        System.out.println(myClass.getPositive()); // >0
    }

    static class MyClass {
        @RandomInteger
        private int negative;
        @RandomInteger
        private int positive;

        public int getNegative() {
            return negative;
        }

        public int getPositive() {
            return positive;
        }
    }
}
