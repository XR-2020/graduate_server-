package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 评估中心项目实体
* */
public class PingGuZhongXinXiangGuanUO implements Serializable {
    private static final long serialVersionUID=1L;

    private String finishtime;
    private String grade;
    private String partment;
    private String name;
    private Integer[] people;
    private Integer role;

    public Integer getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "PingGuZhongXinXiangGuanUO{" +
                "finishtime='" + finishtime + '\'' +
                ", grade='" + grade + '\'' +
                ", partment='" + partment + '\'' +
                ", name='" + name + '\'' +
                ", people=" + Arrays.toString(people) +
                '}';
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

}
