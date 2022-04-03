package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 教研论文实体
* */

public class JiaoYanLunWenUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String finishtime;
    private String partment;
    private Integer[] people;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "JiaoYanLunWenUO{" +
                "name='" + name + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", partment='" + partment + '\'' +
                ", people=" + Arrays.toString(people) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }
}
