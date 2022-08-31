package Homework1.Task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {5, 6, 3, 2, 5, 1, 4, 9};

        System.out.println("Cортировка от меньшего к большему методом прямого включения: " + Arrays.toString(sortMinToMax(array)));
        int[] array1 = {1, 2, 3, 4, 5, 5, 6, 9};
        assert (Arrays.equals(sortMinToMax(array), array1)); //проверка 1


        System.out.println("Cортировка от большего к меньшему методом прямого включения: " + Arrays.toString(sortMaxToMin(array)));
        int[] array2 = {9, 6, 5, 5, 4, 3, 2, 1};
        assert (Arrays.equals(sortMaxToMin(array), array2)); //проверка 2

    }

    private static int[] sortMinToMax(int[] array) {
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    m = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = m;
                }
            }
        }
        return array;
    }

    private static int[] sortMaxToMin(int[] array) {
        int m = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] > array[j - 1]) {
                    m = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = m;
                }
            }
        }
        return array;
    }


}

