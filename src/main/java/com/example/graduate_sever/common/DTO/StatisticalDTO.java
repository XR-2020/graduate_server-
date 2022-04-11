package com.example.graduate_sever.common.DTO;

public class StatisticalDTO {
    private String date1;
    private String date2;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "StatisticalDTO{" +
                "date1='" + date1 + '\'' +
                ", date2='" + date2 + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
