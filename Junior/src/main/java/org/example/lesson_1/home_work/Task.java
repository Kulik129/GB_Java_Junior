package org.example.lesson_1.home_work;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 *
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */


public class Task {
    public static void main(String[] args) {
        List<Integer> list = generateRandomNumber(1_000_000, 1_000);

        int max = maxNumber(list);
        System.out.println("\nМаксимальное число из списка чисел: " + max);

        int ans = calculateSumForNumbersAboveThreshold(list, 500_000, 5, 150);
        System.out.println("\nСумма чисел: " + ans);

        long res = countForNumbersLessThreshold(list, 100_000);
        System.out.println("\nКоличество чисел, квадрат которых меньше 100_000: " + res);

        List<Employee> employees = Employee.employees();

        System.out.println();
        Stream<Employee> employeeStream = department(employees, "Стажер");
        employeeStream.forEach(System.out::println);

        System.out.println();
        List<Employee> employees1 = salaryIncrease(employees, 10_000, 1.20);
        employees1.forEach(System.out::println);

        System.out.println();
        department(employees);

        System.out.println();
        salary(employees);
    }

    /**
     * Список рандомных чисел.
     *
     * @return список рандомных чисел из n элементов.
     */
    public static List<Integer> generateRandomNumber(int bound, int maxSize) {
        List<Integer> numbers = Stream.generate(() -> ThreadLocalRandom.current().nextInt(bound))
                .limit(maxSize)
                .collect(Collectors.toList());
        return numbers;
    }

    /**
     * Максимальное число
     *
     * @param randomNumber список из рандомных чисел.
     * @return максимальное число из списка.
     */
    public static int maxNumber(List<Integer> randomNumber) {
        Optional<Integer> max = randomNumber.stream().max(Comparator.naturalOrder());
        int maxNumb = max.get();
        return maxNumb;
    }

    /**
     * Все числа, большие, чем N, умножить на M, отнять от них D и просуммировать
     *
     * @param list       Список из рандомных чисел.
     * @param max        Максимальное число.
     * @param multiplier Множитель.
     * @param deductible Делитель.
     * @return Сумма всех чисел.
     */
    public static int calculateSumForNumbersAboveThreshold(List<Integer> list, int max, int multiplier, int deductible) {
        int res = list.stream()
                .filter(it -> it > max)
                .map(it -> (it * multiplier) - deductible)
                .mapToInt(it -> it)
                .sum();
        return res;
    }

    /**
     * Найти количество чисел, квадрат которых меньше, чем N.
     *
     * @param list   список из рандомных чисел.
     * @param number Задаваемое число для условия.
     * @return количество чисел квадрат которых меньше, чем задаваемый параметр.
     */
    public static long countForNumbersLessThreshold(List<Integer> list, int number) {
        long res = list.stream()
                .filter(num -> num * num < number)
                .count();
        return res;
    }

    /**
     * Вывести список сотрудников по отделу.
     *
     * @param employees Список сотрудников
     * @param dep       Отдел.
     * @return Список сотрудников
     */
    public static Stream<Employee> department(List<Employee> employees, String dep) {
        return employees.stream()
                .filter(it -> it.getDepartment().equals(dep));
    }

    /**
     * Увеличение зарплаты.
     *
     * @param employees Список сотрудников.
     * @param minSalary Минимальная зарплата.
     * @param increase  Коэффициент умножения зарплаты.
     * @return Список сотрудников с увеличенной зарплатой.
     */
    public static List<Employee> salaryIncrease(List<Employee> employees, int minSalary, double increase) {
        employees.stream()
                .filter(it -> it.getSalary() < minSalary)
                .forEach(it -> it.setSalary(it.getSalary() * increase));
        return employees;
    }

    /**
     * Map с отделами и сотрудниками внутри отдела
     *
     * @param employees Список сотрудников.
     */
    public static void department(List<Employee> employees) {
        Map<String, List<Employee>> dep = employees.stream()
                .collect(Collectors.groupingBy(it -> it.getDepartment()));
        dep.forEach((department, employeeList) -> {
            System.out.println("Отдел: " + department);
            System.out.println("Сотрудники: " + employeeList);
        });
    }

    /**
     * Map с отделами и средней зарплатой внутри отдела
     *
     * @param employees Список сотрудников.
     */
    public static void salary(List<Employee> employees) {
        Map<String, Double> sal = employees.stream()
                .collect(Collectors.groupingBy(it -> it.getDepartment(), Collectors.averagingDouble(it -> it.getSalary())));
        sal.forEach((department, salary) -> {
            System.out.println("Отдел: " + department);
            System.out.println("Средняя зп: " + salary);
        });
    }
}
