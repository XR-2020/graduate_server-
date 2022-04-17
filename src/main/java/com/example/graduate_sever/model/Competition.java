package com.example.graduate_sever.model;

import java.io.Serializable;

/*
* 学科竞赛实体
* */
public class Competition implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private String name;
    private String grade;
    private String level;
    private Integer status;
    private String finishtime;
    private String student;
    private String teacher;
    private String shenbao;
    private byte[] metails;
    private String partment;

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getShenbao() {
        return shenbao;
    }

    public void setShenbao(String shenbao) {
        this.shenbao = shenbao;
    }

    public byte[] getMetails() {
        return metails;
    }

    public void setMetails(byte[] metails) {
        this.metails = metails;
    }
}
