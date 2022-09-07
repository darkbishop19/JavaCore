package Homework2;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        Map<String, Long> mapToSort = Arrays.stream(RAW_DATA)
                .distinct()
                .collect(groupingBy(Person::getName, Collectors.counting()));
        for (Map.Entry<String, Long> entry : mapToSort.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("Value: " + entry.getValue());
        }

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        int[] array = {3, 4, 2, 7};
        int number = 10;
        int start = 0;
        int end = array.length - 1;
        int[] arrayFinal = {-1000, -1000};
        while (start < end) {

            Arrays.sort(array);
            int sum = array[start] + array[end];
            if (sum == number) {
                arrayFinal[0] = array[start];
                arrayFinal[1] = array[end];
                System.out.println(Arrays.toString(arrayFinal));
                break;
            }
            if (array[end] > number) {
                end--;
                continue;
            }
            if (sum > number) {
                end--;
            } else {
                start++;
            }

        }
        int[] arrayTest1 = {3, 7};
        assert (Arrays.equals(arrayFinal, arrayTest1));

        if ( arrayFinal[0] == -1000 && arrayFinal[1] == -1000){
            System.out.println("Array contains zero pairs");
        }



        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
        */
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false

    }

    private static Boolean fuzzySearch(String checkWord, String initialWord) {
        char[] arrayInitial = initialWord.toCharArray();
        int start = 0;
        int end = arrayInitial.length - 1;
        char[] arrayCheck = checkWord.toCharArray();
        int startCheck = 0;
        int endCheck = arrayCheck.length - 1;
        char[] arrayCurrentCheck = new char[checkWord.toCharArray().length];

        while (start <= end) {

            if (arrayInitial[start] == arrayCheck[startCheck]) {
                arrayCurrentCheck[startCheck] = arrayCheck[startCheck];
                start++;
                startCheck++;
            } else {
                start++;
            }
            if (arrayInitial[end] == arrayCheck[endCheck]) {
                arrayCurrentCheck[endCheck] = arrayCheck[endCheck];
                end--;
                endCheck--;
            } else {
                end--;
            }
        }

        if (Arrays.equals(arrayCheck, arrayCurrentCheck)) {
            return true;
        }
        return false;
    }

}
