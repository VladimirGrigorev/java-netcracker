package com.app.repository;

import com.app.Sorting;
import com.app.entities.IPerson;
import com.app.entities.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.io.FileReader;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Класс списка персон
 * @autor Григорьев Владимир
 * @version 1
 */
public class Repository<T> implements IRepository {

    /** Поле количества начальных пустых элементов массива */
    private final int INIT_SIZE = 10;

    /** Поле массива данных */
    private IPerson[] array = new IPerson[INIT_SIZE];

    /** Поле указатель */
    private int pointer = 0;

    /**
     * Функция добавляющая новый элемент в список. При достижении размера внутреннего
     * массива происходит его увеличение на один
     * @param item - элемент для добавления
     */
    public void add(IPerson item) {
        if(pointer == array.length)
            resize(array.length+1);
        array[pointer++] = item;
    }

    /**
     * Функция возращающая элемент списка по индексу
     * @param index - индекс элемента
     * @return возвращает элемент списка
     */
    public IPerson get(int index) {
        return array[index];
    }

    /**
     * Процедура определения персоны
     * @param index
     * @param person
     */
    public void set(int index, IPerson person) {
        array[index] = person;
    }

    /**
     * Метод добавления персоны
     * @param index - индекс
     * @param person - персона
     */
    public void add(int index, IPerson person) {
        array[index] = person;
    }

    /**
     * Функция удаления элемента списка по индексу. Все элементы справа от удаляемого
     * перемещаются на шаг налево.
     * @param index - индекс удаляемого элемента
     */
    public void delete(int index) {
        if (pointer - index >= 0) System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE)
            resize(array.length-1);
    }

    /**
     * Функция возвращающая количество элементов в списке
     * @return возвращает количество элементов в списке
     */
    public int size() {
        return pointer;
    }

    /**
     * Вспомогательный метод для изменения размера массива
     * @param newLength - новая длина
     */
    private void resize(int newLength) {
        Person[] newArray = new Person[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /**
     * Функция возращающая содержимое списка в виде строкового массива
     * @return возвращает содержимое списка в виде строкового массива
     */
    public String[] toPrint() {
        String[] list = new String[pointer];
        for(int i=0; i<pointer; i++)
            list[i] = array[i].toString();
        return list;
    }

    public List<IPerson> toList()
    {
        return Arrays.asList(array);
    }

    /**
     * Функция сортировки коллекции
     * @param comparator - сравниватель
     */
    public void sortBy(Comparator<IPerson> comparator ){
        if(array.length < 50){
            Sorting.shellSort(array, comparator);
        }
        else {
            Sorting.quickSort(array, 0, array.length - 1, comparator);
        }
    }

    /**
     * Функция поиска по предикату
     * @param predicate - предикат
     * @return коллекцию персон
     */
    public IRepository searchBy(Predicate<IPerson> predicate) {
        IRepository res = new Repository<IPerson>();
        for (int index = 0; index < array.length; index++) {
            if (predicate.test(array[index]))
                res.add(array[index]);
        }
        return res;
    }
}
