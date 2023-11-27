<div align="center">
    <h1> Лямбда </h1>
</div>

### Лямбда выражение - это синтаксический способ создать объект, класс которого реализует интерфейс из одного метода.
### Comparator - это интерфейс, который принимает два объекта какого-то типа, тип задается в джинерике и возвращает число.
### Если первый объект короче второго тем он меньше, значит возвращаем любое отрицательное число, 
### Если первый объект длиннее второго тем он больше, значит возвращаем любое положительное число,
### Если оба аргумента равны, значит возвращаем 0.

```java
public class Example1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C#","C++","C","Kotlin");

        System.out.println(list); // [Java, C#, C++, C, Kotlin]

        list.sort(new StringLength());

        System.out.println(list); // [C, C#, C++, Java, Kotlin]
    }

    static class StringLength implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()) {
                return -1;
            } else if (o1.length() > o2.length()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // То же самое что и выше, только используя анонимный класс.
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C#","C++","C","Kotlin");

        System.out.println(list);

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() > o2.length()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        System.out.println(list);
    }

    // Используя ламбда выражение
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "C#","C++","C","Kotlin");

        System.out.println(list);

        list.sort((o1, o2) -> o1.length() - o2.length());

        System.out.println(list);
    }
}
```

### Пример реализации лямбды без аргументов:
````java
public class Example1 {
    public static void main(String[] args) {
        MyInt myInt = () -> 5;
        int res = myInt.foo();
        System.out.println(res);
    }

    interface MyInt {
        int foo();
    }
}
````

### Пример реализации лямбды c аргументом:
```java
public class Example1 {
    public static void main(String[] args) {

        MyInt myInt = arg -> System.out.println("Реализация кода, который принимает в себя какой-то аргумент, например " + arg);

        myInt.foo("Такой аргумент");

        interface MyInt {
            void foo(String arg);
        }
    }
}
```

---

### Runnable. Рандомное число от 0 - 100
````java
public class Example1 {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(ThreadLocalRandom.current().nextInt(100));
        for (int i = 0; i < 10; i++) {
            runnable.run();
        }
    }
}
````
---

### Встроенные функциональные интерфейсы:
* Predicate<T>

* Consumer<T>

* Function<T,R>

* Supplier<T>

* UnaryOperator<T>

* BinaryOperator<T>

## Predicate проверяет соблюдение некоторого условия. Если оно соблюдается, то возвращается значение true.
```java
public class LambdaApp {
 
    public static void main(String[] args) {
         
        Predicate<Integer> isPositive = x -> x > 0;
         
        System.out.println(isPositive.test(5)); // true
        System.out.println(isPositive.test(-7)); // false
    }
}
```


## Function Принимает два параметра. 1 -й то что он принимает, 2 -й то что он возвращает
````java
public class Example1 {
    public static void main(String[] args) {
        Function<String, Integer> stringIntegerFunction = arg -> arg.length();

        System.out.println(stringIntegerFunction.apply("Java"));  // 4
        System.out.println(stringIntegerFunction.apply("C++"));   // 3
        System.out.println(stringIntegerFunction.apply("Swift")); // 5
        System.out.println(stringIntegerFunction.apply("C#"));    // 2
    }
}
````

## Consumer<T> выполняет некоторое действие над объектом типа T, при этом ничего не возвращая:
```java
public class LambdaApp {
 
    public static void main(String[] args) {
         
        Consumer<Integer> printer = x -> System.out.printf("%d долларов \n", x);
        printer.accept(600); // 600 долларов
    }
}
```

## Supplier<T> не принимает никаких аргументов, но должен возвращать объект типа T:
```java
public class LambdaApp {

    public static void main(String[] args) {

        Supplier<User> userFactory = () -> {

            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя: ");
            String name = in.nextLine();
            return new User(name);
        };

        User user1 = userFactory.get();
        User user2 = userFactory.get();

        System.out.println("Имя user1: " + user1.getName());
        System.out.println("Имя user2: " + user2.getName());
    }
}

class User {

    private String name;

    String getName() {
        return name;
    }

    User(String n) {
        this.name = n;
    }
}
```

---

### Метод референс(Ссылки на методы). Пример выше можно заменить ссылкой на методы.
```java
public class Example1 {
    public static void main(String[] args) {
        Function<String, String> function  = Example1::upper;
        System.out.println(function.apply("DMitrii"));

        Function<String, Integer> len = Example1::len;
        System.out.println(len.apply("Java"));

        Runnable res = Example1::rand;
        for (int i = 0; i < 10; i++) {
            res.run();
        }

        List<String> languages = Arrays.asList("Java","Kotlin", "C++","Swift");
        languages.sort(Example1::comp);
        System.out.println(languages);
    }

    static String upper(String s) {
        return s.toUpperCase();
    }

    static Integer len(String s) {
        return s.length();
    }

    static void rand() {
        System.out.println(ThreadLocalRandom.current().nextInt(100));
    }

    static int comp(String a, String b) {
        return a.length() - b.length();
    }
}
```

### Метод референс может быть не только на методы, но и на объекты:
```java
public class Example1 {
    public static void main(String[] args) {
        Supplier<Integer> stringSupplier = "Java"::length;
        System.out.println(stringSupplier.get());

        Predicate<String> stringPredicate = "Java"::equals;
        System.out.println(stringPredicate.test("Java"));
        System.out.println(stringPredicate.test("C++"));
        System.out.println(stringPredicate.test("Swift"));
    }
}
```

### Метод референс может быть не только на метод, но и на конструктор:
```java
public class Example1 {
    public static void main(String[] args) {
        Supplier<Person> personGenerate = Person::new;
        Person person = personGenerate.get();
        System.out.println(person);
        person = personGenerate.get();
        System.out.println(person);
    }
    public static class Person {
        private static long counter = 1L;
        private String name;

        public Person() {
            name = "Person #" + counter++;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
// конструктор с параметрами
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

        public Person(String n) {
            this.name = n;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
```
<div align="center">
    <h1> Stream </h1>
</div>

### Стрим - это такая структура как некий поток данных (текущий поток), с этим потоком можно что-то делать, фильтровать менять и т.д.
#### У стирмов есть два типа операций: 
1. Промежуточная. Меняет текущий стрим.
2. Терминальная. Выводит какой-то результат.

## Промежуточные операции. Intermediate
### Данные операции удобно воспринимать как отложенные – они выполнятся когда их запустит конечная терминальная операция над стримом.

* **_Провести операцию над каждым элементом_** `peek()`
Аналог `forEach()`, только промежуточный (нетерминальный)
Если в метод `peek()` передать функцию `System.out::println`, тогда все объекты будут выводиться на экран в момент, когда они будут проходить через поток.

```
Stream.of("a", "b", "c").peek(System.out::println);
```

---

* **_Преобразовать данные из одного типа в другой_** `map()`
  Можно передать функцию, которая преобразовывает один тип данных в другой.
```
Stream.of(1, 2, 3).map((x) -> String.valueOf(x));

Stream.of(1, 2, 3).map(String::valueOf);        // лямбда выражение

Stream.of("1", "2", "3").map(Integer::parseInt);
```

___

* **_Отфильтровать элементы_** `filter()`
```
Stream.of(1, 2, 3, 4, 5).filter(n -> n < 4);    // [1, 2, 3]
```

___

* **_Удалить дублирующиеся элементы_** `distinct()`
```
Stream.of(1, 2, 3, 2, 4, 2, 5).distinct();      // [1, 2, 3, 4, 5]

```
___
* **_Сортировка и обратная сортировка элементов_** `sorted()`
```
Stream.of(4, 2, 3, 5, 1).sorted();              // [1, 2, 3, 4, 5]

Stream.of(4, 2, 3, 5, 1).sorted(Comparator.reverseOrder())
```

---

* **_Лимит количества элементов_** `limit()`
```
Stream.of(1, 2, 3, 4, 5, 6).limit(3);            // [1, 2, 3]
```

___

* **_Пропустить первые элементы_** `skip()`
```
Stream.of(1, 2, 3, 4, 5).skip(2);                // [3, 4, 5]
```
___
* **_Сопоставить поток с развернутым потоком_** `flatMap()`
  Возвращает поток, состоящий из результатов замены каждого элемента этого потока содержимым сопоставленного потока, 
полученного путем применения предоставленной функции сопоставления к каждому элементу.
```
List<String> petNames = person.stream()
        .flatMap(person -> person.getPetName().stream())
        .collect(Collectors.toList());

System.out.println(petNames);             // [pet1, pet2, pet3, pet4, pet5]
```
___
# Конечные операции. Terminal
Запускают всю цепь промежуточных операций и возвращают конечный результат, закрывают поток.
Собрать элементы потока и преобразовать их к нужному типу `collect()`
В аргумент метода нужно передать объект `Collector`.
* **_Преобразовать поток в_** `List Collectors.toList()`
```
List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
```
___
* **_Преобразовать поток в строку_** `String Collectors.joining()`
```
String collect = Stream.of("a", "b", "c").collect(Collectors.joining());
```
___
* **_Итерация по каждому элементу_** `forEach()`
```
Stream.of("a", "b", "c").forEach(System.out::println);
```
___
* **_Узнать количество элементов стрима_** `count()`
```
Stream.of("a", "b", "c").count();
```
___
* **_Найти минимальное и максимальное значение_** `min()` и `max()`
Сравнение происходит с помощью объекта `Comparator`.
Возвращают объект класса `Optional` – объект-контейнер, который может хранить null.
Метод `get()` – возвращает `значение`, которое хранит объект `Optional`.
```
Optional<Integer> max = Stream.of(4, 2, 3, 5, 1)
.max(Comparator.naturalOrder());

Integer maximum = max.get();

Integer minimum = Stream.of(4, 2, 3, 5, 1)
.min(Comparator.naturalOrder())
.get();
```
___
* **_Comparator удобно задать с помощью лямбда-функции:_**
```
Stream.of("a", "bb", "ccc")
.min((s1, s2) -> s1.length() - s2.length())
.get();

Stream.of("a", "bb", "ccc")
.max(Comparator.comparingInt(String::length))
.get();
```
___
* **_Найти первый подходящий элемент_** `findFirst()`
Возвращает первый подходящий элемент из стрима и завершается.
Возвращают объект класса `Optional`.
```
Stream.of(1, 2, 3, 4, 5)
.filter(e -> e % 2 == 0)
.findFirst()
.get();
```
___
* **_Найти любой подходящий элемент_** `findAny()`
Возвращает любой подходящий элемент из стрима и завершается.
Аналог метода `findFirst()` для потоков, которые обрабатываются параллельно.
Найденный элемент необязательно будет первый по порядку в потоке.
```
Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
.filter(e -> e % 2 == 0)
.parallel()
.findAny()
.get();
```
___
* **_Все элементы соответствуют условию_** `allMatch()`
```
Stream.of(1, 2, 3, 4, 5).allMatch(e -> e > 0);     // true
Все элементы НЕ соответствуют условию noneMatch()
Stream.of(1, 2, 3, 4, 5).noneMatch(e -> e > 0);    // false
Хотя бы один элемент соответствует условию anyMatch()
Stream.of(1, 2, 3, 4, 5).anyMatch(e -> e > 4);     // true
```
* **_Сумма элементов стрима_** `sum()`
Это метод классов-стримов примитивных типов данных:
```
IntStream, LongStream и DoubleStream
List<Integer> integers = new ArrayList<>();

integers.stream()
.mapToInt(i -> i)
.sum();
```
___
* **_Операция сведения_** `Stream.reduce()`
Позволяет получить один результат из последовательности элементов, неоднократно применяя операцию комбинирования к элементам в последовательности.
* Участники операции сведения:
1. Identity - элемент, который является начальным значением операции сокращения и результатом по умолчанию, если поток пуст.
2. Accumulator - функция, которая принимает два параметра: частичный результат операции сведения и следующий элемент потока
3. Combiner - функция, используемая для объединения частичного результата операции сокращения и типами реализации аккумулятора.
Если используем последовательные потоки, типы аргументов аккумулятора и типы его реализации не совпадают – нужно использовать Combiner.
___ 

* **_Сумма элементов списка_**
```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

int sum = numbers.stream()
.reduce(0, Integer::sum);

int sum2 = numbers.stream()
.reduce(0, (subtotal, element) -> subtotal + element);
```
___

* **_Сумма элементов в параллельном потоке_**
В таких случаях нужно использовать функцию для объединения результатов подпотоков в один – это роль `Combiner` Комбинатора.
В приведенном примере эту роль выполняет метод `Integer::sum`
```
int sum = numbers
.parallelStream()
.reduce(0, (a, b) -> a + b, Integer::sum);

int sum2 = ages
.parallelStream()
.reduce(0, Integer::sum, Integer::sum);
```
___
* **_Объединение списка строк в одну сроку_**
```
List<String> letters = Arrays.asList("a", "b", "c", "d", "e");

String result = letters.stream()
.reduce("", String::concat);

String result2 = letters.stream()
.reduce("", (partialString, element) -> partialString + element);
```

### Класс Optional
Методы интерфейса **_Stream_** `findAny()`, `findFirst()`, `max()`, `min()` и `reduce()` возвращают объект класса `Optional`
```
public final class Optional<T> extends Object
```
Это объект-контейнер, который может содержать или не содержать нулевое значение.
Ссылка на объект класса `Optional` может быть `null`
Если значение присутствует, метод `isPresent()` вернет `true`
Позволяет избавиться от проверки на `null`
Без этого класса приходилось писать проверку на `NullPointerException`.
Благодарю этому один объект `Optional` можно сравнить с другим объектом `Optional` через метод `equals()`, даже если они хранят в себе ссылки на `null`.
---
* **_Получить элемент_** `Optional` `get()`
Метод класса `Optoinal` – возвращает значение, если оно присутствует, в противном случае бросит `NoSuchElementException` .
```
* Stream.of("1", "22", "333")
.max(Comparator.comparingInt(String::length))
.get();
```
---
* **_Класс_** `Collectors`
Метод `collect()` интерфейса Stream собирает данные в необходимую структуру данных, например в коллекции — `List<T>, Set<T>, Map<T, R>`
```
public interface Collector<T,A,R>
```
___
В метод `collect()` в качестве параметра принимает объект типа `Collector`.
Статические методы класса `Collectors` возвращают такой объект класса `Collector`.
```
public final class Collectors extends Object
```
___
Класс `Collectors` содержит статические методы для сбора элементов в коллекцию, обобщения и группировки элементов в соответствии с различными критериями и т. п., которые возвращают готовые объекты коллекций.
* **_Преобразование потока в_** `List toList()`
```
list.stream().collect(Collectors.toList());
```
___
* **_Преобразование потока в Set_** `toSet()`
```
list.stream().collect(Collectors.toSet());
```
___

* **_Преобразование потока в Map_** `toMap()`
```
map.entrySet().stream()
.map(e -> String.valueOf(e).split("="))
.collect(Collectors.toMap(e -> e[0], e -> e[1]));
```
___

* ***_Объединение элементов в строку String_** `joining()`
Объединение потока коллекции `List` в одну строку через запятую
```
list.stream().collect(Collectors.joining(", "));
```
___
* **Сумма элементов потока_** `summingInt()`, `summingDouble()`, `summingLong()`
Например, сумма заработной платы сотрудников
```
List<Employee> employees = new ArrayList<>()

employees.stream()
.collect(Collectors.summingDouble(Employee::getSalary)));
```
___
* **_Сгруппировать элементы по условию_** `groupingBy()`
Группировка людей по стране
```
List<Person> people = new ArrayList<>()

people.stream()
.collect(Collectors.groupingBy(Person::getCountry));
```