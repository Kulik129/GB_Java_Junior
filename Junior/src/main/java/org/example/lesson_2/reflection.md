Рефлексия — это механизм исследования данных о программе во время её выполнения. 
Рефлексия позволяет исследовать информацию о полях, методах и конструкторах классов.

Есть класс:
````java
static class Person {
        private final String name;
        private int age;

        public Person(String name) {
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
````
Можно создать объект так:
````java
public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = new Person("Dmitrii");
        System.out.println(person);
    }
}
````

Создать объект с помощью рефлексии:
````java
public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;  // Получил объект типа Class
        Constructor<Person> constructor = personClass.getConstructor(String.class);  // Вызываем конструктор
        Person person1 = constructor.newInstance("Dmitrii"); // Вызываем конструктор с помощью newInstance() и передаем аргументы
        System.out.println(person1);
    }
}
````
Когда вызываем метод getConstructor() это соответствует:
````
public Person(String name) {
            this(name, 20);
        }
````

По сути объект созданный таким образом: 
 ```
 Person person1 = constructor.newInstance("Dmitrii");
 ```
Ничем не отличается от создания объекта привычным способом
```
Person person = new Person("Dmitrii");
```

Центральная идея в том, чтобы работать с разными синтаксическими структурами как с объектами.

С помощью `setAccessible(true)` и `getDeclaredConstructor()` можно обращаться к приватному конструктору 

````java
public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass1 = Person.class;
        Constructor<Person> declaredConstructor = personClass1.getDeclaredConstructor(String.class); // getDeclaredConstructor() принимает массив параметров и вызывает приватный конструктор
        declaredConstructor.setAccessible(true); // позволяет вызывать приватный конструктор
        Person person2 = declaredConstructor.newInstance("Dmitrii");
        System.out.println(person2);
    }
}
````


> `setAccessible` позволяет вызывать то что нельзя вызывать

Мы можем сменить параметры через наследника тоже
Создадим класс и наследуемся от Person: 
````java
public class Head extends Person {
    public Head(String name, int age) {
        super(name, age);
    }
}
````

Сможем вызвать приватный конструктор родительского класса 
````java
public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? super Head> superclass = Head.class.getSuperclass();
        Constructor<? super Head> declaredConstructor1 = superclass.getDeclaredConstructor(String.class);
        declaredConstructor1.setAccessible(true);
        Object object = declaredConstructor1.newInstance("new");
        System.out.println(object); // new
    }
}
````


Чтобы пометь age в классе Person нужно создать объект, который описывает поле
````java
public class Example {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person dmitrii = constructor.newInstance("Dmitrii");
        Field age = personClass.getDeclaredField("age"); // Создаем объект, который описывает поле
        age.setAccessible(true);
        age.setInt(dmitrii, 160);
        System.out.println(dmitrii);
    }
}
````

Аналогично можно сменить поля
````java
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
````