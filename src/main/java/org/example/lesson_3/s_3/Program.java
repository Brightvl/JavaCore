package org.example.lesson_3.s_3;

import java.util.ArrayList;
import java.util.Collection;

public class Program {

    public static void main(String[] args) {
        Human human1 = Human.create("Name #1", 37, 100, 1000);
        Runner human2 = Human.create("Name #2", 37, 100, 1000);
        BaseHuman human3 = Human.create("Name #3", 37, 100, 1000);
        Object human4 = Human.create("Name #4", 37, 100, 1000);
        Cat cat1 = Cat.create("Cat #1", 200, 100);
        cat1.setColor("Рыжий");
        Collection<Runner> collection = new ArrayList<>();
        collection.add(human2);
        collection.add(human1);
        //collection.add(human3);
        //collection.add(human4);
        collection.add(cat1);
        collection.add(Robot.create("Robot #1", 200, 100));
        processRunner(collection);
    }

    public static void processRunner(Collection<Runner> runners){
        for (Runner runner: runners) {
            runner.run(100);
            if (runner instanceof Cat){
                Cat cat =  (Cat)runner;
                System.out.println(cat.getColor());
            }
        }
    }

}
