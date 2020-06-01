package com.app.sorter;

import java.util.Comparator;

public class QuickSort implements ISorter {

    /**
     * Быстрая сортировка
     * @param array - массив
     * @param comparator - сравниватель
     */
    @Override
    public <T> void sort(Object[] array, Comparator<T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }


    private  <T> void quickSort(Object[] array, int leftBorder, int rightBorder, Comparator<T> comparator) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        Object pivot = array[(leftMarker + rightMarker) / 2];
        do {

            while (comparator.compare((T) array[leftMarker], (T) pivot) < 0) {
                leftMarker++;
            }

            while (comparator.compare((T) array[rightMarker], (T) pivot) > 0) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {

                if (leftMarker < rightMarker) {
                    Object tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }

                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(array, leftMarker, rightBorder, comparator);
        }
        if (leftBorder < rightMarker) {
            quickSort(array, leftBorder, rightMarker, comparator);
        }
    }
}
