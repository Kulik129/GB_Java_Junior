Сериализация это механизм в джава который позволяется объекты классов переводить в байты и обратно байты переводить в объекты.
### Объект
```java
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
```
### Сериализация объекта в файл:
```java
public class Example {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializablePerson serializablePerson = new SerializablePerson("Dmitrii");
        OutputStream outputStream = Files.newOutputStream(Path.of("output.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(serializablePerson);
        objectOutputStream.close();
    }
}
```
```
�� sr 'org.example.lesson_3.SerializablePerson�%DfQO�U I ageL namet Ljava/lang/String;xp   t Dmitrii
```
Десериализация из файла:
```java
public class Example {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path path = Path.of("output.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
        Object deserialized = objectInputStream.readObject();
        System.out.println(deserialized);
        objectInputStream.close();
    }
}
```
```
SerializablePerson{name='Dmitrii', age=20}
```