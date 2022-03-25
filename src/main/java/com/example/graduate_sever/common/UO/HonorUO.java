package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 荣誉称号实体
* */
public class HonorUO implements Serializable {
    private static final long serialVersionUID=1L;

    private String name;
    private String level;
    private String finishtime;
    private Integer[] people;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "HonorUO{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", people=" + Arrays.toString(people) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }
}
