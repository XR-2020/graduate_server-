package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 合并申报参数接收实体
* */
public class NewSystemUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private Integer type;
    private String partment;
    private String finishtime;
    private Integer[] people;
    private Integer role;
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

    public Integer getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
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

    @Override
    public String toString() {
        return "HeBingUO{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", partment='" + partment + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", people=" + Arrays.toString(people) +
                '}';
    }
}
