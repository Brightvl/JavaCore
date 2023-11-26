package org.example.lesson_3.hw_3.empoyee;


/**
 * (для запоминания) Comparable - сравнимый, интерфейс обеспечивает естественный порядок для объектов этого класса
 */
public abstract class Employee implements Comparable<Employee> {


    private String name;
    private double monthlySalary;

    public Employee(String name, double salary) {
        this.name = name;
        this.monthlySalary = salary;
    }

    /**
     * Расчет среднемесячной зарплаты
     *
     * @return
     */
    public abstract Double calculationOfAverageMonthlySalary();

    /**
     * (для запоминания) compareTo - (дословно) по сравнению с.
     * Дефолтная реализация сравнения, в данном случае по именам, чтобы создать другие виды сравнения, нужно создать
     * класс который осуществит интерфейс Compare<Employee>
     *
     * @return Отрицательное число, если вызывающий объект меньше other.
     * Положительное число, если вызывающий объект больше other.
     * Ноль, если объекты равны.
     */
    @Override
    public int compareTo(Employee other) {
        // Сравнение по именам
        return this.name.compareTo(other.name);
    }


    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        return String.format("%s ≈ %.1f₽", name, calculationOfAverageMonthlySalary());
    }
}
