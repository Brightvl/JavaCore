package org.example.lesson_3.hw_3.empoyee.comparators;

import org.example.lesson_3.hw_3.empoyee.Employee;

import java.util.Comparator;

/**
 * (Для запоминания) Comparator позволяет создавать свой порядок сравнения для объектов нужного класса
 * Дефолтный метод сравнения может быть реализован в данный момент по имени работников
 */
public class ComparatorAverageMonthlySalary implements Comparator<Employee> {
    /**
     * Cравнение по среднему заработку за месяц
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return Отрицательное число, если o1 меньше o2.
     * Положительное число, если o1 больше o2.
     * Ноль, если объекты равны.
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.calculationOfAverageMonthlySalary(), o1.calculationOfAverageMonthlySalary());
    }
}
