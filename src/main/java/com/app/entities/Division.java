package com.app.entities;

public class Division implements IDivision {

    public Integer id;

    public String name;

    public Division(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Division(String name) {
        this.id = 0;
        this.name = name;
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
}
