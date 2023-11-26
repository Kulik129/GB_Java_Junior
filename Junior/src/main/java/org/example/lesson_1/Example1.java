package org.example.lesson_1;

import java.util.function.Function;
import java.util.function.Supplier;

public class Example1 {
    public static void main(String[] args) {
        Function<String, Person> personGenerate = Person::new;
        Person person = personGenerate.apply("Dmitrii");
        System.out.println(person);
        person = personGenerate.apply("Kate");
        System.out.println(person);
    }
    public static class Person {
        private static long counter = 1L;
        private String name;

        private Supplier<String> wordGenerator;

        public void saySomething(){
            System.out.println(wordGenerator.get());
        }
        private String generatorNextWord(){
            return "random";
        }

        public Person(String n) {
            this.name = n;
            this.wordGenerator = this::generatorNextWord;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}