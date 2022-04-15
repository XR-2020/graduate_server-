package com.example.graduate_sever.common.EditUO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 荣誉称号实体
* */
public class EditHonorUO implements Serializable {
    private static final long serialVersionUID=1L;

    private String name;
    private String level;
    private String finishtime;
    private Integer[] people;
    private Integer role;
    private String partment;
    private Integer shenbao;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

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
