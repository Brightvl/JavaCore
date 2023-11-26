package org.example.lesson_3.s_3;

public class Human extends BaseHuman implements Runner {

    //region Методы

    @Override
    public void printDisplayInfo() {
        System.out.printf("%s - %d - %d - %d\n", name, age, maxRun, maxJump);
    }

    public static Human create(String name, int age, int maxRun, int maxJump){
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя человека.");
        }
        if (age < 18){
            throw new RuntimeException("Некорректный возраст человека.");
        }
        return new Human(name, age, maxRun, maxJump);
    }

    @Override
    public String toString() {
        return String.format("%s - %d - %d - %d\n", name, age, maxRun, maxJump);
    }

    //endregion

    //region Конструкторы

    public Human(String name, int age, int maxRun, int maxJump){
        super(name, age);
        this.maxRun = maxRun;
        this.maxJump = maxJump;

    }

    //endregion

    //region Свойства

    public int getMaxRun() {
        return maxRun;
    }

    public void setMaxRun(int maxRun) {
        this.maxRun = maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(int maxJump) {
        this.maxJump = maxJump;
    }

    //endregion

    //region Поля

    private int maxRun;
    private int maxJump;

    //endregion

}
