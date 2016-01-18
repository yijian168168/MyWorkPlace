package com.bubbleSort;

import java.util.Comparator;

/**
 * 冒泡排序算法
 */
public class BubbleSorter{

    public <T extends Comparable<T>> void sort(T[] list) {
        boolean bubble = true;
        for (int i = 1, len = list.length; i < len && bubble; ++i) {
            bubble = false;
            for (int j = 0; j < len - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    bubble = true;
                }
            }
        }
    }

    public <T> void sort(T[] list, Comparator<T> comp) {
        boolean bubble = true;
        for (int i = 1, len = list.length; i < len && bubble; ++i) {
            bubble = false;
            for (int j = 0; j < len - i; ++j) {
                if (comp.compare(list[j], list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    bubble = true;
                }
            }
        }
    }
}
