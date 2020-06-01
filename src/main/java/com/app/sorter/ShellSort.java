package com.app.sorter;

import java.util.Comparator;

public class ShellSort implements ISorter {

    /**
     * Сортировка Шелла
     * @param array - массив
     * @param comparator - сравниватель
     */
    @Override
    public <T> void sort(Object[] array, Comparator<T> comparator){
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (comparator.compare((T) array[c], (T) array[c + gap]) > 0) {
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
    private void swap(Object[] array, int ind1, int ind2) {
        Object tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
