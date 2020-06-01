package com.app.repository;

import com.app.InjectorException;
import com.app.entities.Person;
import com.app.injector.ILabInjector;
import com.app.injector.LabInjector;
import com.app.sorter.ISorter;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс списка персон
 * @autor Григорьев Владимир
 * @version 1
 */
public class Repository<T> implements IRepository<T> {

    /** Поле количества начальных пустых элементов массива */
    private final int INIT_SIZE = 10;

    /** Поле массива данных */
    private Object[] array = new Object[INIT_SIZE];

    /** Поле указатель */
    private int pointer = 0;

    /** Поле сортровки*/
    @ILabInjector
    private ISorter mysorting;

    public Repository() throws InjectorException {
        (new LabInjector()).inject(this);
    }

    /**
     * Функция добавляющая новый элемент в список. При достижении размера внутреннего
     * массива происходит его увеличение на один
     * @param item - элемент для добавления
     */
    public void add(T item) {
        if(pointer == array.length)
            resize(array.length+1);
        array[pointer++] = item;
    }

    /**
     * Функция возращающая элемент списка по индексу
     * @param index - индекс элемента
     * @return возвращает элемент списка
     */
    public T get(int index) {
        return (T) array[index];
    }

    /**
     * Процедура определения персоны
     * @param index -
     * @param person -
     */
    public T set(int index, T person) {
        array[index] = person;
        return (T) array[index];
    }

    /**
     * Метод добавления персоны
     * @param index - индекс
     * @param person - персона
     */
    public void add(int index, T person) {
        array[index] = person;
    }

    /**
     * Функция удаления элемента списка по индексу. Все элементы справа от удаляемого
     * перемещаются на шаг налево.
     * @param index - индекс удаляемого элемента
     */
    public T delete(int index) {
        if (pointer - index >= 0) System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE)
            resize(array.length-1);
        return (T) array[index];
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

    public List<T> toList()
    {
        return Arrays.asList((T) array);
    }

    public List<Person> toPersonList()
    {
        ArrayList<Person> list = new ArrayList<Person>();
        for (Object o : array) {
            list.add((Person) o);
        }
        return list;
    }

    /**
     * Функция сортировки коллекции
     * @param comparator - сравниватель
     */
    public void sortBy(Comparator<T> comparator ){
        mysorting.sort(array, comparator);
    }

    /**
     * Функция поиска по предикату
     * @param predicate - предикат
     * @return коллекцию персон
     */
    public IRepository searchBy(Predicate<T> predicate) {
        IRepository res = null;
        try {
            res = new Repository<IPerson>();
        } catch (InjectorException e) {
            e.printStackTrace();
        }
        for (Object o : array) {
            if (predicate.test((T) o))
                res.add(o);
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository<?> that = (Repository<?>) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
