package com.app.entities;

import ru.vsu.lab.entities.IDivision;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Objects;

@XmlRootElement(name = "division")
@XmlAccessorType(XmlAccessType.FIELD)
public class Division implements IDivision {

    public Integer id;

    public String name;

    /**
     * Конструктор подразделения принимающий код подразделения и имя подразделения
     * @param id -
     * @param name -
     */
    public Division(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Конструктор подразделения принимающий строку
     * @param name - имя подразделения
     */
    public Division(String name) {
        this.id = 0;
        this.name = name;
    }

    /**
     * Пустой конструктор подразделения
     */
    public Division() {
    }

    public static class Adapter extends XmlAdapter<Division, IDivision> {
        public IDivision unmarshal(Division v) { return v; }
        public Division marshal(IDivision v) { return (Division) v; }
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(id, division.id) &&
                Objects.equals(name, division.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
