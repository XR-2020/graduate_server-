package com.example.graduate_sever.common;

import java.util.List;

public class TableData {
    private Object object;
    private List<People> people;

    public TableData(Object object, List<People> people) {
        this.object = object;
        this.people = people;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
