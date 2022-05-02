package com.example.graduate_sever.common;

public class NewGetPageTotal {
    private String tablename;
    private String type;

    public NewGetPageTotal(String tablename, String type) {
        this.tablename = tablename;
        this.type = type;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
