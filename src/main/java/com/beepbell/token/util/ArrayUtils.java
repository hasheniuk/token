package com.beepbell.token.util;

public class ArrayUtils {

    public static void swap(char[] arr, int i, int j) {
        if (i == j) return;
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private ArrayUtils() {}
}
