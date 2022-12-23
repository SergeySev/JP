package org.example.algoritm;

import java.util.ArrayList;

public class ArrayAlgorithm {
    /**
     * Даны два целых числа x и n, напишите функцию для вычисления x^n
     * решение 1 - рекурсивно O(n)
     */
    public static int exponentiation(int x, int n) {
        return help(x, x, n);
    }

    public static int help(int x, int a, int n) {
        if (n == 1) {
            return a;
        }
        return help(x, a*x, --n);
    }

    /**
     * Решение 2 - улучшить решение 1 до O(lon n)
     * Вот мои рассуждения:
     * if 2 = x*x           || 2 = 2(x * x)              -> 1 = return x             -> 2 * 1       || 2
     * if 3 = x*x*x         || 3 = 2(x * x)              -> 1 =  2 * x               -> 2 * x       || 3
     * if 6 = x^3 * x^3     || 6 = 1(x * x * x)          -> 3 = 2(x * x * x)         -> 1 * 2       || 6
     * if 7 = x^3 * x^3 * x || 7 = 1(x * x * x)          -> 3 = 2(x * x * x)         -> 1 * 2 * x   || 7
     * if 8 = x^4 * x^4     || 8 = 1(x * x * x * x)      -> 4 = 2(x * x * x * x)     -> 1 * 2       || 8
     * if 10= x^5 * x^5     || 10 = 1(x * x * x * x * x) -> 5 = 2(x * x * x * x * x) -> 1 * 2       || 10
     * if 11= x^5 * x^5 * x || 11 = 1(x * x * x * x * x) -> 5 = 2(x * x * x * x * x) -> 1 * 2 * x   || 11
     * Если верить википедии, то это бинарное возведение в степень.
     * Их и старался реализовать в коде.
     * Но выходит ведь O(n/2)
     */
    public static int fastPow(int x, final int n) {
        if (n == 1) {
            return x;
        }
        int half = halfPow(x,n);
        if (n % 2 == 1) {
            return half * half * x;
        } else {
            return half * half;
        }
    }

    public static int halfPow(int x, final int n) {
        for (int i = 1; i < n / 2; i++) {
            x *= x;
        }
        return x;
    }

    /**
     * А эту функцию я честно списал. Из-за побитовых сдвигов её сложно понять
     * и даже придумать такое. Я смотрел, что такое побитовые операции, но так и не понял
     * как их можно предсказывать и рассчитывать.
     */
    public static double BinaryPower(double b, long e) {
        double v = 1d;
        int count = 0;
        System.out.println("Before cycle");
        System.out.println("Count = " + count);
        System.out.println("v = " + v);
        System.out.println("e = " + e);
        System.out.println("b = " + b + "\n");
        while (e > 0) {
            count++;
            if ((e & 1) != 0) {
                System.out.println("In if cycle");
                v *= b;
            }
            b *= b;
            e >>= 1;
            System.out.println("Count = " + count);
            System.out.println("v = " + v);
            System.out.println("e = " + e);
            System.out.println("b = " + b + "\n");
        }
        return v;
    }

    /**
     * Имея два отсортированных массива размера m и n соответственно,
     * вам нужно найти элемент, который будет находиться на k-й позиции в конечном отсортированном массиве.
     * Массив 1 - 100 112 256 349 770
     * Массив 2 - 72 86 113 119 265 445 892
     * к = 7
     * Вывод: 256
     * Окончательный отсортированный массив -
     * 72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892
     * 7-й элемент этого массива равен 256.
     */
    public static Integer findK(int[] arr1, int[] arr2, final int k) {
        int sumTwoLength = arr1.length + arr2.length;

        // K cannot be bigger, than array length and cannot be less 1
        if (k > sumTwoLength || k < 1) {
            return null;
        }

        // Create variable for next using
        int index = 0;
        int i = 0;
        int numberInFirstArray;

        int low = 0;
        int height = arr2.length - 1;
        int midl;

        // In this array add number from two arrays
        int[] totalArray = new int[sumTwoLength];

        // I am using a binary search in while cycle for
        // find a place for number
        while (totalArray[k - 1] == 0 && i < arr1.length) {
            midl = (low + height) / 2;
            numberInFirstArray = arr1[i];

            if (height - low == 2 && numberInFirstArray < arr2[midl]) {
                // Program found a place for a number and can
                // move on in next iteration
                totalArray[index + midl] = numberInFirstArray;
                index++;
                i++;
                continue;

            } else if (height - low == 2 && numberInFirstArray > arr2[midl]) {
                // Program found a place for a number and can
                // move on in next iteration
                totalArray[index + midl + 2] = numberInFirstArray;
                index += 2;
                i++;
                continue;
            }
            if (numberInFirstArray < arr2[midl]) {
                // If not found a place, set a new value for height or low
                // for next step of binary search
                height = midl;
            } else {
                low = midl;
            }
        }
        // In previously cycle program try to found a number
        // in first array. If the number was not found, program continue and looking
        // this number in second array with the same way: add number from
        // second array in total array
        i = 0;
        int j = 0;
        while (totalArray[k - 1] == 0) {
            if (totalArray[i] == 0) {
                totalArray[i] = arr2[j];
                j++;
            }
            i++;
        }
        return totalArray[k - 1];
    }

    /**
     * Имея отсортированный массив arr[] и число x,
     * напишите функцию, которая подсчитывает вхождения x в arr[]. Ожидаемая временная сложность O(Log n)
     * arr[] = {1, 1, 2, 2, 2, 2, 3,}
     * x = 2
     * Вывод: 4 раза
     */
    public static int quantityXInArray(int x, int[] arr) {
        // using a binary search.
        // if the number was found - remember the index of this number and count++
        // move left from midl while lookingNumber == number
        // move right from midl while lookingNumber == number
        // return count

        int count = 0;

        int low = 0;
        int height = arr.length - 1;
        int midl = -1;

        // Find x in array with binary search
        while (low <= height) {
            midl = (low + height) / 2;
            if (x == arr[midl]) {
                count++;
                break;
            } else if (x < arr[midl]) {
                height = midl - 1;
                System.out.println("height = " + height);
            } else {
                low = midl + 1;
                System.out.println("low = " + low);
            }
        }
        if (midl == -1) return count;

        // move left
        int i = midl - 1;
        while (i > -1 && arr[i] == x) {
            count++;
            i--;
        }
        //move right
        i = midl + 1;
        while (i < arr.length && arr[i] == x) {
            count++;
            i++;
        }
        return count;
    }

    /**
     * 4* Найдите пиковый элемент в двумерном массиве
     * Элемент является пиковым, если он больше или равен
     * своим четырем соседям слева, справа, сверху и снизу.
     * Например, соседями для
     * A[i][j] являются
     * A[i-1][j],
     * A[i+1][j],
     * A[i][j-1],
     * A[i][j+1].
     * <p>
     * Для угловых элементов отсутствующие соседи считаются
     * отрицательными бесконечными значениями.
     * 10 20 15
     * 21 30 14
     *  7 16 32
     * Выход: 30
     * 30 — пиковый элемент, потому что все его
     * соседи меньше или равны ему.
     * 32 также можно выбрать в качестве пика.
     */
    /* Note
     * 1. Смежная диагональ не считается соседней.
     * 2. Пиковый элемент необязательно является максимальным элементом.
     * 3. Таких элементов может быть несколько.
     * 4. Всегда есть пиковый элемент.
     */
    public static ArrayList<Integer> peakElement(int[][] arr1) {
        // Так как в условии не указано, что двумерный массив отсортирован,
        // значит остается линейный поиск.
        ArrayList<Integer> peeksArray = new ArrayList<>();

        boolean up;
        boolean down;
        boolean left;
        boolean right;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {

                up = (i == 0) || arr1[i][j] >= arr1[i - 1][j];
                down = (i == arr1.length - 1) || arr1[i][j] >= arr1[i + 1][j];

                left = (j == 0) || arr1[i][j] >= arr1[i][j - 1];
                right = (j == arr1[i].length - 1) || arr1[i][j] >= arr1[i][j + 1];

                if (up && down && left && right) {
                    peeksArray.add(arr1[i][j]);
                }
            }
        }
        return peeksArray;
    }


    public static void main(String[] args) {
        // First task
        System.out.println("1) Tests for exponentiation function");
        System.out.println(Math.pow(2, 2) + "      |   " + exponentiation(2, 2));
        System.out.println(Math.pow(2, 3) + "      |   " + exponentiation(2, 3));
        System.out.println(Math.pow(2, 4) + "     |   " + exponentiation(2, 4));
        System.out.println(Math.pow(2, 5) + "     |   " + exponentiation(2, 5));


        System.out.println("\nTests for fastRecPow");
        System.out.println(Math.pow(2, 2) + "      |   " + fastPow(2, 2));
        System.out.println(Math.pow(2, 3) + "      |   " + fastPow(2, 3));
        System.out.println(Math.pow(2, 4) + "     |   " + fastPow(2, 4));
        System.out.println(Math.pow(2, 5) + "     |   " + fastPow(2, 5));

        //Second task
        int[] arr1 = {100, 112, 256, 349, 770};
        int[] arr2 = {72, 86, 113, 119, 265, 445, 892};

        System.out.println("\n2) K in array = " + findK(arr1, arr2, 12) + "\n");

        System.out.println("________\nHere i tried to understand binary power");
//        System.out.println(BinaryPower(2, 9));

        //Third task
        int[] arr = {1, 1, 2, 2, 2, 2, 3};
        System.out.println("\n3) Quantity 2 in array = " + quantityXInArray(2, arr) + "\n");

        //Fourth task
        int[][] array = {{10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}};

        System.out.println("\n4) Peek element in array = " + peakElement(array) + "\n");
    }
}
