package com.app.entities;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.YEARS;

/**
 * Класс персоны
 * @autor Григорьев Владимир
 * @version 1
 */
public class Person implements IPerson {

    /** Поле код */
    private Integer id;

    /** Поле имя */
    private String firstName;

    /** Поле фамилия */
    private String lastName;

    /** Поле дата рождения */
    private LocalDate birthdate;

    /** Поле пол */
    private Gender gender;

    /** Поле подразделение */
    private IDivision division;

    /** Поле зарплата */
    private BigDecimal salary;

    /**
     * Конструктор персоны
     * @param id -
     * @param firstName -
     * @param lastName -
     * @param birthdate -
     * @param gender -
     * @param division -
     * @param salary -
     */
    public Person(Integer id, String firstName, String lastName, LocalDate birthdate, Gender gender,
                  IDivision division, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
    }

    /**
     * Пустой конструктор персоны
     */
    public Person() {
    }


    /**
     * Функция получения значения кода {@link Person#id}
     * @return возвращает значение кода
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Процедура определения кода
     * @param id - код
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Функция получения значения имени
     * @return возвращает значение имени
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Процедура определения имени
     * @param firstName - имя
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Функция получения значения фамилии
     * @return возвращает значение фамилии
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Процедура определения фамилии
     * @param lastName - фамилия
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Функция получения значения дататы рождения
     * @return возвращает значение дататы рождения
     */
    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Процедура определения дня рождения
     * @param birthdate - день рождения
     */
    @Override
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Функция получения значения пола
     * @return возвращает значение пола
     */
    @Override
    public Gender getGender() {
        return gender;
    }

    /**
     * Процедура определения пола
     * @param gender - пол
     */
    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Функция получения значения подразделения
     * @return возвращает значение подразделения
     */
    @Override
    public IDivision getDivision() {
        return division;
    }

    /**
     * Процедура определения подразделения
     * @param division - подразделение
     */
    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    /**
     * Функция получения значения зарплаты
     * @return возвращает значение зарплаты
     */
    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Процедура определения зарплаты {@link Person#id}
     * @param salary - зарплата
     */
    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Функция получения значения возраста
     * @return возвращает значение возраста
     */
    @Override
    public Integer getAge()
    {
        return (int)YEARS.between(birthdate, LocalDate.now());
    }

    /**
     * Функция получения строкового представления персоны
     * @return возвращает строковое представление персоны
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", division=" + division +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(birthdate, person.birthdate) &&
                gender == person.gender &&
                Objects.equals(division, person.division) &&
                Objects.equals(salary, person.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthdate, gender, division, salary);
    }
}
