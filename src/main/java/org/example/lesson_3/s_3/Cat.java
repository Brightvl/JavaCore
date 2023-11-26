package org.example.lesson_3.s_3;

public class Cat extends BaseCat implements Runner {

    //region Методы

    public static Cat create(String name, int maxRun, int maxJump) {
        return new Cat(name, maxRun, maxJump);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //endregion

    //region Конструкторы

    private Cat(String name, int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.name = name;
    }

    //endregion

    //region Поля

    private String color;

    private int maxRun;
    private int maxJump;

    private String name;

    @Override
    public int getMaxJump() {
        return maxJump;
    }

    @Override
    public int getMaxRun() {
        return maxRun;
    }

    @Override
    public String getName() {
        return name;
    }

    //endregion

}
