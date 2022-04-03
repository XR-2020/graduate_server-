package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 教研项目实体
* */

public class JiaoYanXiangMuUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private Integer[] people;
    private String finishtime;
    private String lianghua;
    private String wenhao;
    private String partment;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "JiaoYanXiangMuUO{" +
                "name='" + name + '\'' +
                ", people=" + Arrays.toString(people) +
                ", finishtime='" + finishtime + '\'' +
                ", lianghua='" + lianghua + '\'' +
                ", wenhao='" + wenhao + '\'' +
                ", partment='" + partment + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
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

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }
}
