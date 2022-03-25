package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 纵向科研项目实体
* */
public class ZongXiangKeYanXiangMuUO implements Serializable {
    private static final long serialVersionUID=1L;

    private String finishtime;
    private String level;
    private String type;
    private String lixiang;
    private String partment;
    private String name;
    private Integer[] people;
    private Integer firstpeople;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "ZongXiangKeYanXiangMuUO{" +
                "finishtime='" + finishtime + '\'' +
                ", level='" + level + '\'' +
                ", type='" + type + '\'' +
                ", lixiang='" + lixiang + '\'' +
                ", partment='" + partment + '\'' +
                ", name='" + name + '\'' +
                ", people=" + Arrays.toString(people) +
                ", firstpeople=" + firstpeople +
                '}';
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLixiang() {
        return lixiang;
    }

    public void setLixiang(String lixiang) {
        this.lixiang = lixiang;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
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

    public Integer getFirstpeople() {
        return firstpeople;
    }

    public void setFirstpeople(Integer firstpeople) {
        this.firstpeople = firstpeople;
    }
}
