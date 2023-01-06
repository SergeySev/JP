package org.example.streem;

import java.util.Arrays;

public class Stream {
    /*
     * Написать метод, который проходится по массиву Int[].
     * Умножает каждое
     * четное число на 3
     * не четное на 5,
     * далее надо отфильтровать числа которые больше 25
     * посчитать их сумму
     * вывести на экран.
     */
    public static void homeTask(int[] arr) {
        int result = Arrays.stream(arr).map(x-> {
            if (x%2 == 0) {
                x*=3;
            } else x*=5;
            return x;
        })
                .filter(x->x>25)
                .reduce(Integer::sum)
                .getAsInt();
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9,  10};
        homeTask(array);
    }
}
