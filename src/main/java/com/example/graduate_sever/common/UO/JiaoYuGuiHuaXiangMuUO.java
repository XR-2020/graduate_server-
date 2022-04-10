package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 教育规划项目实体
* */

public class JiaoYuGuiHuaXiangMuUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String finishtime;
    private Integer[] people;
    private String partment;
    private String grade;
    private String level;
    private String danwei;
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

    @Override
    public String toString() {
        return "JiaoYuGuiHuaXiangMuUO{" +
                "name='" + name + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", people=" + Arrays.toString(people) +
                ", partment='" + partment + '\'' +
                ", grade='" + grade + '\'' +
                ", level='" + level + '\'' +
                ", danwei='" + danwei + '\'' +
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

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
}
