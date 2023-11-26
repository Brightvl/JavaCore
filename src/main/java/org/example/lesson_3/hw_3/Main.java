package org.example.lesson_3.hw_3;

import org.example.lesson_3.hw_3.empoyee.Employee;
import org.example.lesson_3.hw_3.empoyee.Freelancer;
import org.example.lesson_3.hw_3.empoyee.Worker;
import org.example.lesson_3.hw_3.empoyee.comparators.ComparatorAverageMonthlySalary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1. Построить три класса (базовый и 2 потомка), описывающих некоторых работников с почасовой оплатой (один из
 * потомков - Freelancer) и фиксированной оплатой (второй потомок -Worker).
 * <p></p>
 * а) Описать в базовом классе абстрактный метод для расчёта среднемесячной заработной платы.
 * Для «повременщиков» формула для расчета такова: «среднемесячная заработная плата = 20.8 * 8 * почасовая ставка»,
 * для работников с фиксированной оплатой «среднемесячная заработная плата = фиксированная месячная оплата».
 * <p></p>
 * б) Создать на базе абстрактного класса массив/коллекцию сотрудников и заполнить его.
 * <p></p>
 * в) ** Реализовать интерфейсы для возможности сортировки массива/коллекции (обратите ваше внимание на интерфейсы
 * Comparator, Comparable), добавьте новое состояние на урове базового типа и создайте свой уникальный компаратор.
 * <p></p>
 * г) ** Создать класс, содержащий массив или коллекцию сотрудников (как Worker, так и Freelancer), и реализовать
 * возможность вывода данных с использованием foreach (подсказка: вам потребуется поработать с интерфейсом Iterable).
 */
public class Main {
    public static void main(String[] args) {


        //а,б)
        Employee[] employees = {
                Worker.create("Worker 3", 2500),
                Worker.create("Worker 2", 3000),
                Worker.create("Worker 1", 2400),
                Freelancer.create("Freelancer 1", 15.5),
                Freelancer.create("Freelancer 3", 14),
                Freelancer.create("Freelancer 2", 17.5)};

        //в)
        List<Employee> employeeList = Arrays.asList(employees);
        System.out.printf("Было:\n%s\n\n", employeeList);
        Collections.sort(employeeList);
        System.out.printf("Сортировка базовая(по имени):\n%s\n\n", employeeList);
        employeeList.sort(new ComparatorAverageMonthlySalary());
        System.out.printf("Сортировка по средней заработной плате:\n%s\n\n", employeeList);

        //г)
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.addEmployees(employees);

        for (Employee employee : employeeManagement) {
            if (employee instanceof Worker) {
                System.out.println(employee.calculationOfAverageMonthlySalary());
            }
            if (employee instanceof Freelancer) {
                System.out.println(employee.calculationOfAverageMonthlySalary());
            }
        }
    }
}

