package com.app.entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static ru.vsu.lab.entities.enums.Gender.MALE;

public class PersonTest {

    private static Person p;

    @Before
    public void setUp() throws Exception {
        LocalDate timePoint = LocalDate.of(1999, 12, 30);
        Division division = new Division(0, "A");
        p = new Person(0, "first name", "last name",
                    timePoint, MALE, division, new BigDecimal("1111.222"));
    }

    @Test
    public void getId() {
        int actual = p.getId();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getFirstName() {
        String actual = p.getFirstName();
        String expected = "first name";
        assertEquals(expected, actual);
    }

    @Test
    public void getLastName() {
        String actual = p.getLastName();
        String expected = "last name";
        assertEquals(expected, actual);
    }

    @Test
    public void getBirthdate() {
        LocalDate actual = p.getBirthdate();
        LocalDate expected = LocalDate.of(1999, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void getGender() {
        Gender actual = p.getGender();
        Gender expected = MALE;
        assertEquals(expected, actual);
    }

    @Test
    public void getDivision() {
        Division actual = (Division) p.getDivision();
        Division expected = new Division(0, "A");
        assertEquals(expected, actual);
    }

    @Test
    public void getSalary() {
        BigDecimal actual = p.getSalary();
        BigDecimal expected = new BigDecimal("1111.222");
        assertEquals(expected, actual);
    }

    @Test
    public void getAge() {
        int actual = p.getAge();
        int expected = 19;
        assertEquals(expected, actual);
    }
}