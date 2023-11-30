package org.example.lesson_4.s_4.task_2;

import org.example.lesson_4.s_4.task_1.v_2.Result;
import org.example.lesson_4.s_4.task_1.v_2.ValidationError;

public class Task1V2 {

    public static void main(String[] args) {

        ArrayUtilsV2 arrayUtils = new ArrayUtilsV2();

        for (int i = 0; i < 10; i++){
            System.out.printf("\n***** Итерация %d *****\n", i + 1);
            testArray(arrayUtils);
        }

    }

    private static void testArray(ArrayUtilsV2 arrayUtils){
        Result<Integer> result = arrayUtils.processArray(arrayUtils.generateArray());

        System.out.printf("Сумма значений всех элементов массива: %d\n", result.getValue());
        if (!result.getErrors().isEmpty()){
            System.out.println("Ошибки валидации: ");
            for (ValidationError validationError: result.getErrors()) {
                System.out.println(validationError);
            }
        }


    }

}
