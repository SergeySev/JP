package org.example.algoritm;
import java.util.Arrays;

/*
    2 задание - переместить три диска.
    START
    Create massive with massive: {[3,2,1],[],[]}

    Put 1 in third massive: {[3,2,1],[],[1]}
    Delete 1 from first massive: {[3,2],[],[1]}

    Put 2 in second massive: {[3,2],[2],[1]}
    Delete 2 from first massive: {[3],[2],[1]}

    Put 1 in second massive: {[3],[2,1],[1]}
    Delete 1 from third massive: {[3],[2,1],[]}

    Put 3 in third massive: {[3],[2,1],[3]}
    Delete 3 from third massive: {[],[2,1],[3]}

    Put 1 in first massive: {[1],[2,1],[3]}
    Delete 1 from second massive: {[1],[2],[3]}

    Put 2 in third massive: {[1],[2],[3,2]}
    Delete 2 from second massive: {[1],[],[3,2]}

    Put 1 in third massive: {[1],[],[3,2,1]}
    Delete 1 from first massive: {[],[],[3,2,1]}

    END
 */

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
        for (int i :
                array) {
            System.out.println(binarySearch(array, i, 0, array.length - 1));
        }
        for (int i :
                array) {
            System.out.println(isInArray(array, i));
        }
    }
}
