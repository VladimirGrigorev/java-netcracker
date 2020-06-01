package com.app.sorter;

import java.util.Comparator;

public interface ISorter {

    <T> void sort(Object[] arr, Comparator<T> sorter);
}
