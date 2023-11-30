package org.example.lesson_4.s_4.task_1.v_2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Task2 {

    /**
     Задача 2
     ========
     Запишите в файл следующие строки:

     Анна=4
     Елена=5
     Марина=6
     Владимир=8
     Константин=10
     Иван=4
     */

    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println(prepareFile());

    }

    static int prepareFile(){

        try (FileWriter fileWriter = new FileWriter("names.txt")){
            fileWriter.write("Анна=4\n");
            fileWriter.write("Елена=5\n");
            fileWriter.write("Марина=6\n");
            /*(if (random.nextInt(2) == 0){
                throw new IOException("Database Error!");
            }*/
            fileWriter.write("Владимир=8\n");
            fileWriter.write("Константин=10\n");
            fileWriter.write("Иван=4\n");
            return 2;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return 1;
        }

    }







}
