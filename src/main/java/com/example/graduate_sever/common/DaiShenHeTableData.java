package com.example.graduate_sever.common;

import java.util.List;

public class DaiShenHeTableData {
    private Object object;
    private List<People> people;
    private String tablename;


    public DaiShenHeTableData(Object object, List<People> people, String tablename) {
        this.object = object;
        this.people = people;
        this.tablename = tablename;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
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

    @Override
    public String toString() {
        return "TableData{" +
                "object=" + object +
                ", people=" + people +
                '}';
    }
}
