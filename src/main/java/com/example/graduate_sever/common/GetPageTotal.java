package com.example.graduate_sever.common;

public class GetPageTotal {
    private String tablename;
    private Integer type;

    public GetPageTotal(String tablename, Integer type) {
        this.tablename = tablename;
        this.type = type;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
