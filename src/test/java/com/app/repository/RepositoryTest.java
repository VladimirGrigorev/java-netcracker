package com.app.repository;

import com.app.InjectorException;
import com.app.entities.Division;
import com.app.entities.Person;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static ru.vsu.lab.entities.enums.Gender.FEMALE;
import static ru.vsu.lab.entities.enums.Gender.MALE;

public class RepositoryTest {

    private Repository<IPerson> repository = new Repository<IPerson>();

    public RepositoryTest() throws InjectorException {
    }

    @Before
    public void setUp() throws Exception {
        repository.add(new Person(0, "first name 3", "last name 1",
                LocalDate.of(1999, 12, 30), MALE,
                new Division(0, "A"), new BigDecimal("1111.222")));
        repository.add(new Person(1, "first name 1", "last name 2",
                LocalDate.of(1111, 1, 1), FEMALE,
                new Division(1, "B"), new BigDecimal("2222.111")));
        repository.add(new Person(2, "first name 2", "last name 3",
                LocalDate.of(2000, 1, 1), MALE,
                new Division(1, "B"), new BigDecimal("1111.111")));
    }

    @Test
    public void get() {
        IPerson actual = repository.get(0);
        Person expected = new Person(0, "first name 3", "last name 1",
                LocalDate.of(1999, 12, 30), MALE,
                new Division(0, "A"), new BigDecimal("1111.222"));
        assertEquals(expected, actual);
    }

    @Test
    public void delete() throws InjectorException {
        repository.delete(1);
        Repository<Person> expected = new Repository<Person>();
        expected.add(new Person(0, "first name 3", "last name 1",
                LocalDate.of(1999, 12, 30), MALE,
                new Division(0, "A"), new BigDecimal("1111.222")));
        expected.add(new Person(2, "first name 2", "last name 3",
                LocalDate.of(2000, 1, 1), MALE,
                new Division(1, "B"), new BigDecimal("1111.111")));

        assertEquals(expected, repository);
    }

    @Test
    public void size() {
        int actual = repository.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void sortBy() throws InjectorException {

        repository.sortBy(new Comparator<IPerson>(){
            public int compare(IPerson o1, IPerson o2) {
                if (o1 != null && o2 != null)
                return o1.getFirstName().compareTo(o2.getFirstName());
                else return 0;
            }
        });

        Repository<Person> expected = new Repository<Person>();
        expected.add(new Person(1, "first name 1", "last name 2",
                LocalDate.of(1111, 1, 1), FEMALE,
                new Division(1, "B"), new BigDecimal("2222.111")));
        expected.add(new Person(2, "first name 2", "last name 3",
                LocalDate.of(2000, 1, 1), MALE,
                new Division(1, "B"), new BigDecimal("1111.111")));
        expected.add(new Person(0, "first name 3", "last name 1",
                LocalDate.of(1999, 12, 30), MALE,
                new Division(0, "A"), new BigDecimal("1111.222")));

        assertEquals(expected, repository);
    }

    @Test
    public void searchBy() throws InjectorException {
        Predicate<IPerson> predicate = x -> x != null && x.getId() == 2;

        IRepository actual = repository.searchBy(predicate);

        Repository<Person> expected = new Repository<Person>();
        expected.add(new Person(2, "first name 2", "last name 3",
                LocalDate.of(2000, 1, 1), MALE,
                new Division(1, "B"), new BigDecimal("1111.111")));

        assertEquals(expected, actual);
    }
}