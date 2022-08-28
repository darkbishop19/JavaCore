package Task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = rnd();
                System.out.print(array[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("Максимум: " + max(array));
        System.out.println("Минимум: " + min(array));
        System.out.println("Среднее: " + avg(array));

    }

    private static int rnd() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.get(0);
    }

    private static int max(int[][] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        return max;
    }

    private static int min(int[][] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }

    private static double avg(int[][] array) {
        int avg = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                avg += array[i][j];
            }
        }
        double avg1 = 0;
        return (double) avg / ((double) array.length * (double) array.length);
    }
}





