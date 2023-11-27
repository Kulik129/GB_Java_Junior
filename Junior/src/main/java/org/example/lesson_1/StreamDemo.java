package org.example.lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        //Найти и распечатать все товары из категории электроника дешевле 10_000
        // Пример без стримов
        for (Product product : products()) {
            if (product.category.equals("Техника") && product.cost < 10000) {
                System.out.println(product);
            }
        }

        // Пример со стримами
        System.out.println("______________________________________________________");
        List<Product> products = products();
        products.stream()
                .filter(it -> it.getCategory().equals("Техника") && it.getCost() < 10000)
                .forEach(System.out::println);

        System.out.println("______________________________________________________");
        System.out.println();
        // Всем продуктам из категории "Продукты" повысить стоимость на 5%
        products.stream()
                .filter(it -> it.getCategory().equals("Продукты"))
                .forEach(it -> it.setCost(it.getCost() * 1.05));
        products.forEach(System.out::println);

        System.out.println("______________________________________________________");
        System.out.println();
        // Все продукты начинающиеся на М собрать в список
        List<Product> products1 = new ArrayList<>();
        products.stream()
                .filter(it -> it.getName().startsWith("М"))
                .collect(Collectors.toCollection(() -> products1));
        products1.forEach(System.out::println);

        System.out.println("______________________________________________________");
        System.out.println();
        Optional<Product> product = products.stream()
                .filter(it -> it.getName().startsWith("М"))
                .findFirst();
        product.ifPresentOrElse(out -> System.out.println(out), () -> System.out.println("Объекта нет"));

        // Генерация случайных чисел от 1 до 1000 с лимитом в 100 элементов
        System.out.println("______________________________________________________");
        System.out.println();
        List<Integer> list = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
                .limit(100)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("Size: " + list.size());
    }

    private static List<Product> products() {
        return List.of(
                new Product("Молоко", 80, "Продукты"),
                new Product("Компьютер", 70000, "Техника"),
                new Product("Монитор", 30000, "Техника"),
                new Product("Мышка", 500, "Техника"),
                new Product("Клавиатура", 4500, "Техника"),
                new Product("Наушники", 20000, "Техника"),
                new Product("Сметана", 70, "Продукты"),
                new Product("Колбаса", 400, "Продукты"),
                new Product("Сыр", 300, "Продукты"),
                new Product("Айфон", 180000, "Техника"),
                new Product("Мак", 200000, "Техника"),
                new Product("Телевизор", 100000, "Техника"),
                new Product("Глицин", 200, "Аптека"),
                new Product("Маска", 33, "Аптека")
        );
    }

    public static class Product {
        private final String name;
        private double cost;
        private String category;

        public Product(String name, double cost, String category) {
            this.name = name;
            this.cost = cost;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("[%s] (cost = %s, category = %s)", name, cost, category);
        }
    }
}
