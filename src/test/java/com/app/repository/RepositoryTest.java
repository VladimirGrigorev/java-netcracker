package com.app.repository;

import com.app.entities.Division;
import com.app.entities.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.app.entities.enums.Gender.FEMALE;
import static com.app.entities.enums.Gender.MALE;
import static org.junit.Assert.*;

public class RepositoryTest {

    private Repository<Person> personList = new Repository<Person>();

    /**
     * Заполение массива тестовыми данными
     */
    private void fillRepository(){
        LocalDate timePoint = LocalDate.of(1999, 12, 30);
        Division division = new Division(0, "A");

        for(int i=0; i<12; i++)
        {
            Person newPerson = new Person(i, String.format("first name %d", i), String.format("last name %d", i),
                    timePoint, MALE, division, new BigDecimal("1234.1234"));
            personList.add(newPerson);
        }
    }

    @Test
    public void add() {

        fillRepository();

        LocalDate timePoint = LocalDate.of(2000, 1, 1);
        Division division = new Division(0, "B");
        personList.add(new Person(11, "first name", "last name",
                timePoint, FEMALE, division, new BigDecimal("1234.1234")));

        for(String person : personList.toPrint()) {
            System.out.println(person);
        }
    }

    @Test
    public void get() {

        fillRepository();

        System.out.println(personList.get(3));
    }

    @Test
    public void set() {
        LocalDate timePoint = LocalDate.of(2000, 1, 1);
        Division division = new Division(0, "B");
        personList.set(3, new Person(11, "first name", "last name",
                timePoint, FEMALE, division, new BigDecimal("1234.1234")));

        for(String person : personList.toPrint()) {
            System.out.println(person);
        }
    }

    @Test
    public void delete() {
    }

    @Test
    public void size() {
    }

    @Test
    public void toPrint() {
    }

    @Test
    public void toList() {
    }

    @Test
    public void sortBy() {
    }

    @Test
    public void searchBy() {
    }

    @Test
    public void addFromFileCSV() {
    }
}