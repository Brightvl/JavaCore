package org.example.lesson_3.s_3;

public class Robot extends BaseRobot implements Runner {

    //region Методы

    public static Robot create(String name, int maxRun, int maxJump){

        return new Robot(name, maxRun, maxJump);
    }

    //endregion

    //region Конструкторы

    private Robot(String name, int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.name = name;
    }

    //endregion

    //region Поля

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
