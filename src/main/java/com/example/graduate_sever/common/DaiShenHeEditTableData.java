package com.example.graduate_sever.common;

import java.util.List;

public class DaiShenHeEditTableData {
    private Object object;
    private List<Integer> people;

    public DaiShenHeEditTableData(Object object, List<Integer> people) {
        this.object = object;
        this.people = people;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Integer> getPeople() {
        return people;
    }

    public void setPeople(List<Integer> people) {
        this.people = people;
    }
}
