package com.example.graduate_sever.common.EditUO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 学科竞赛实体
* */
public class EditCompetitionUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String grade;
    private String level;
    private String finishtime;
    private String student;
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

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CompetitionUO{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", level='" + level + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", student='" + student + '\'' +
                ", people=" + Arrays.toString(people) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }
}
