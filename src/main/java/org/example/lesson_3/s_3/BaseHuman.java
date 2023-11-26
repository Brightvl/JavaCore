package org.example.lesson_3.s_3;

public abstract class BaseHuman {

    protected String name;
    protected int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 3){
            throw new RuntimeException("Некорректное имя человека.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18){
            throw new RuntimeException("Некорректный возраст человека.");
        }
        this.age = age;
    }

    /*protected BaseHuman(){
        this("[Name]");
    }*/

    protected BaseHuman(String name){
        this(name, 18);
    }

    protected BaseHuman(String name, int age){
        this.name = name;
        this.age = age;
        /*if (name == null || name.length() < 3){
            this.name = "[Name]";
        }
        else {
            this.name = name;
        }
        if (age < 18){
            this.age = 18;
        }
        else {
            this.age = age;
        }*/
    }


    public abstract void printDisplayInfo();
    /*public void printDisplayInfo(){
        System.out.printf("%s - %d\n", name, age);
    }*/


}
