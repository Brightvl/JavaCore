package org.example.lesson_4.s_4.task_2;

import org.example.lesson_4.s_4.task_1.v_2.Result;
import org.example.lesson_4.s_4.task_1.v_2.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayUtilsV2 {
    private static final Random random = new Random();

    /**
     * Возвращает сумму значений элементов массива
     * @param array массив
     * @return сумма значений элементов
     */
    public Result<Integer> processArray(String[][] array){
        List<ValidationError> errors = new ArrayList<>();

        if (array.length != 4 && array[0].length != 4)
            errors.add(new ValidationError("Некорректный размер массива"));

        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                sum += parseElement(array[i][j], i, j, errors);
            }
        }
        return new Result<>(sum, errors);
    }

    /**
     * Вспомогательный метод, преобразует значение элемента к числу
     * @param s значение элемента
     * @param x индекс элемента в массиве
     * @param y индекс элемента в массиве
     * @return число
     */
    private static int parseElement(String s, int x, int y, List<ValidationError> errors) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            errors.add(new ValidationError(String.format("Некорректный формат данных по индексу [%d][%d]", x, y)));
            return 0;
        }
    }


    public String[][] generateArray(){
        int add = random.nextInt(2); // 50%
        String[][] array = new String[4 + add][4 + add];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = Integer.toString(random.nextInt(10, 100));
            }
        }
        if (random.nextInt(3) == 0){ //33%
            array[random.nextInt(array.length)][random.nextInt(array.length)] = "abc";
        }
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                System.out.printf("%s\t", array[i][j]);
            }
            System.out.println();
        }
        return array;
    }
}
