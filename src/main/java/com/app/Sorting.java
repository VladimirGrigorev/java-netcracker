package com.app;

import com.app.entities.IPerson;

import java.util.Comparator;

/**
 * Класс сортировок
 */
public class Sorting {

    /**
     * Быстрая сортировка
     * @param array - массив
     * @param leftBorder - левая граница
     * @param rightBorder - правая граница
     * @param comparator - сравниватель
     */
    public static void quickSort(IPerson[] array, int leftBorder, int rightBorder, Comparator<IPerson> comparator) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        IPerson pivot = array[(leftMarker + rightMarker) / 2];
        do {

            while (comparator.compare(array[leftMarker], pivot) < 0) {
                leftMarker++;
            }

            while (comparator.compare(array[rightMarker], pivot) > 0) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {

                if (leftMarker < rightMarker) {
                    IPerson tmp = array[leftMarker];
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

    /**
     * Сортировка Шелла
     * @param array - массив
     * @param comparator - сравниватель
     */
    public static void shellSort(IPerson[] array, Comparator<IPerson> comparator){
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (comparator.compare(array[c], array[c + gap]) > 0) {
                        swap(array, c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }
    }

    /**
     * Процедура обмена
     * @param array - массив
     * @param ind1 - первое число
     * @param ind2 - второе число
     */
    private static void swap(IPerson[] array, int ind1, int ind2) {
        IPerson tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
