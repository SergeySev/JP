package org.example.algoritm;
/*
First level: 1) Реализовать алгоритм бинарного поиска, который находит данный key в данном отстортированном массиве
public int binarySearch(int[] arr, int key)
Описание алгоритма из лекции
    binary search
    problem: find key in sorted array
    returns index of key
    arr = [1,4,7,9,12,15], key = 9
    take element from the middle 7
    compare to key 7 == 9 false
    compare to key 7 < 9
    if middle element less than key repeat procedure for right half of array
    if middle element more  than key repeat procedure for left half of arr
 */

import java.util.Arrays;

public class BinarySearch {
    public static boolean isInArray(Integer[] originArray, final int key) {
        switch (originArray.length) {
            case 0 -> {
                return false;
            }
            case 1 -> {
                return originArray[0] == key;
            }
            case 2 -> {
                return originArray[0] == key || originArray[1] == key;
            }
        }
        int middle = originArray.length / 2;
        if (key == originArray[middle]) {
            return true;
        } else if (key < originArray[middle]) {
            return isInArray(Arrays.copyOfRange(originArray, 0, originArray.length / 2), key);
        } else {
            return isInArray(Arrays.copyOfRange(originArray, originArray.length / 2, originArray.length), key);
        }
    }

    public static int binarySearch(Integer[] originArray, final int key, int low, int height) {
        switch (originArray.length) {
            case 0 -> {
                return -1;
            }
            case 1 -> {
                return (originArray[0] == key) ? 0 : -1;
            }
            case 2 -> {
                if (originArray[0] == key) {
                    return 0;
                } else if (originArray[1] == key) {
                    return 1;
                } else return -1;
            }
        }
        int middle = (low + height) / 2;

        if (key == originArray[middle]) {
            return middle;
        } else if (height - low == 1) {
            if (originArray[low] == key) return low;
            else if (originArray[height] == key) return height;
            else return -1;
        } else if (key < originArray[middle]) {
            return binarySearch(originArray, key, low, middle);
        } else {
            return binarySearch(originArray, key, middle, height);
        }
    }


    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};
//        for (int i :
//                array) {
//            System.out.println(binarySearch(array, i, 0, array.length-1));
//        }

    }

}
