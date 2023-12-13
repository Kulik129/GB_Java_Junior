package org.example.lesson_3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Example {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        SerializablePerson serializablePerson = new SerializablePerson("Dmitrii");
//        OutputStream outputStream = Files.newOutputStream(Path.of("output.txt"));
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(serializablePerson);
//        objectOutputStream.close();
//        SerializablePerson dmitrii = new SerializablePerson("Dmitrii");
        Path path = Path.of("output.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
        Object deserialized = objectInputStream.readObject();
        System.out.println(deserialized);
        objectInputStream.close();
    }
}
