package org.example.lesson_4.s_4.task_1;

import java.util.Random;

public class ArrayUtils {

    private static final Random random = new Random();

    /**
     * Возвращает сумму значений элементов массива
     * @param array массив
     * @return сумма значений элементов
     * @throws MyArraySizeException некорректный размер массива
     * @throws MyArrayDataException Некорректный формат данных
     */
    public int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException{
        if (array.length != 4 && array[0].length != 4)
            throw new MyArraySizeException("Некорректный размер массива", array.length, array[0].length);

        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                sum += parseElement(array[i][j], i, j);
            }
        }
        return sum;
    }

    /**
     * Вспомогательный метод, преобразует значение элемента к числу
     * @param s значение элемента
     * @param x индекс элемента в массиве
     * @param y индекс элемента в массиве
     * @return число
     * @throws MyArrayDataException Некорректный формат данных
     */
    private static int parseElement(String s, int x, int y) throws MyArrayDataException {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            throw new MyArrayDataException("Некорректный формат данных", x, y);
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
