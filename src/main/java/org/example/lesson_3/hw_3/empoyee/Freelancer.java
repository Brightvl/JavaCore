package org.example.lesson_3.hw_3.empoyee;

/**
 * Работник с почасовой оплатой
 */
public class Freelancer extends Employee {

    //region Поля
    private double hourlyRate;

    //endregion

    //region Конструктор
    public Freelancer(String name, double hourlyRate) {
        super(name, 0);
        this.hourlyRate = hourlyRate;
    }
    //endregion

    //region Методы

    /**
     * Фабричный метод для создания объекта
     *
     * @param name
     * @return
     */
    public static Freelancer create(String name, double hourlyRate) {
        return new Freelancer(name, hourlyRate);
    }

    @Override
    public Double calculationOfAverageMonthlySalary() {
        //Среднемесячная заработная плата = 20.8 * 8 * почасовая ставка
        return 20.8 * 8 * this.hourlyRate;
    }


    //endregion


}

