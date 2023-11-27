package org.example.lesson_1.home_work;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;
    private String department;

    @Override
    public String toString() {
        return String.format("[%s] (Имя: %s, Возраст: %s, З/п: %s)", department, name, age, salary);
    }

    /**
     * @return Список из 20 сотрудников
     */
    public static List<Employee> employees() {
        return List.of(
                new Employee("Иван", 23, 9_000, "Стажер"),
                new Employee("Мария", 25, 15_000, "Рабочий"),
                new Employee("Николай", 25, 12_000, "Рабочий"),
                new Employee("Василий", 25, 19_000, "Инженер"),
                new Employee("Владимир", 25, 4_000, "Стажер"),
                new Employee("Алексей", 25, 7_000, "Стажер"),
                new Employee("Евгений", 25, 9_000, "Стажер"),
                new Employee("Михаил", 25, 10_000, "Рабочий"),
                new Employee("Вараздат", 25, 15_000, "Уборщик"),
                new Employee("Александр", 25, 12_000, "Рабочий"),
                new Employee("Яков", 25, 15_450, "Уборщик"),
                new Employee("Дмитрий", 25, 17_900, "Инженер"),
                new Employee("Игорь", 25, 12_200, "Уборщик"),
                new Employee("Валерий", 25, 18_400, "Инженер"),
                new Employee("Виталий", 25, 15_444, "Уборщик"),
                new Employee("Владислав", 25, 18_897, "Инженер"),
                new Employee("Ростислав", 25, 13_987, "Уборщик"),
                new Employee("Андрей", 25, 11_123, "Рабочий"),
                new Employee("Максим", 25, 5_000, "Стажер"),
                new Employee("Ярослав", 25, 8_000, "Стажер")
        );
    }
}
