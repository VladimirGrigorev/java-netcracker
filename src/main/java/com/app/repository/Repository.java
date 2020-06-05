package com.app.repository;

import com.app.InjectorException;
import com.app.entities.Person;
import com.app.injector.ILabInjector;
import com.app.injector.LabInjector;
import com.app.sorter.ISorter;
import com.app.sorter.QuickSort;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс списка персон
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(Repository.Adapter.class)
public class Repository<T> implements IRepository<T> {

    /** Логгер */
    private static Logger log = Logger.getLogger(Repository.class.getName());

    /** Поле количества начальных пустых элементов массива */
    private final int INIT_SIZE = 10;

    /** Поле массива данных */
    @XmlElement(name = "array")
    private T[] array = (T[]) new Object[10];

    /** Поле указатель */
    private int pointer = 0;

    /** Поле сортровки*/
    @ILabInjector
    @XmlElement
    @XmlJavaTypeAdapter(QuickSort.Adapter.class)
    private ISorter mysorting;

    public Repository() throws InjectorException {
        (new LabInjector()).inject(this);
    }

    public static class Adapter extends XmlAdapter<Repository, IRepository> {
        public IRepository unmarshal(Repository r) { return r; }
        public Repository marshal(IRepository r) { return (Repository) r; }
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
        log.info("Person added to repository.");
    }

    /**
     * Функция возращающая элемент списка по индексу
     * @param index - индекс элемента
     * @return возвращает элемент списка
     */
    public T get(int index) {
        log.info("The person was got");
        return (T) array[index];
    }

    /**
     * Процедура определения персоны
     * @param index -
     * @param person -
     */
    public T set(int index, T person) {
        array[index] = person;
        log.info("The person was set");
        return (T) array[index];
    }

    /**
     * Метод добавления персоны
     * @param index - индекс
     * @param person - персона
     */
    public void add(int index, T person) {
        array[index] = person;
        log.info("Person added to repository.");
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
        log.log(Level.INFO,"The person {} was deleted", index);
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
        array = (T[]) newArray;
    }

    /**
     * Функция возращающая содержимое списка в виде строкового массива
     * @return возвращает содержимое списка в виде строкового массива
     */
    public String[] toPrint() {
        String[] list = new String[pointer];
        for(int i=0; i<pointer; i++)
            list[i] = array[i].toString();
        log.info("The persons were returned");
        return list;
    }

    public List<T> toList()
    {
        log.info("The persons were returned");
        return Arrays.asList((T) array);
    }

    public List<Person> toPersonList()
    {
        ArrayList<Person> list = new ArrayList<Person>();
        for (Object o : array) {
            list.add((Person) o);
        }
        log.info("The persons were returned");
        return list;
    }

    /**
     * Функция сортировки коллекции
     * @param comparator - сравниватель
     */
    public void sortBy(Comparator<T> comparator ){
        mysorting.sort(array, comparator);
        log.info("The persons were sorted");
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
        log.info("The person was find");
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
