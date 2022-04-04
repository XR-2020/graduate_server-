package com.example.graduate_sever.entity;

import java.io.Serializable;

public class ZheXianEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String date1;
    private String date2;

    public ZheXianEntity(String tablename, String date1, String date2) {
        this.name = tablename;
        this.date1 = date1;
        this.date2 = date2;
    }

    public String getTablename() {
        return name;
    }

    public void setTablename(String tablename) {
        this.name = tablename;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
}
