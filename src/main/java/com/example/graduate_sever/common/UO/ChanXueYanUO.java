package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/*
* 产学研实体
* */

public class ChanXueYanUO implements Serializable{
    private static final long serialVersionUID=1L;
    private String name;
    private String partment;
    private String finishtime;
    private String lianghua;
    private String wenhao;
    private Integer[] people;
    private Integer role;
    private Integer shenbao;

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    public String getLianghua() {
        return lianghua;
    }

    public void setLianghua(String lianghua) {
        this.lianghua = lianghua;
    }

    public String getWenhao() {
        return wenhao;
    }

    public void setWenhao(String wenhao) {
        this.wenhao = wenhao;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }


    @Override
    public String toString() {
        return "ChanXueYanUO{" +
                "name='" + name + '\'' +
                ", partment='" + partment + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", lianghua='" + lianghua + '\'' +
                ", wenhao='" + wenhao + '\'' +
                ", people=" + Arrays.toString(people)  +
                '}';
    }
}
