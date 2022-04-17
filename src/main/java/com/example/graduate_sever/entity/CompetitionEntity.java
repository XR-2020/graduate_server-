package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 学科竞赛实体
* */
public class CompetitionEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String grade;
    private String level;
    private Integer status=0;
    private String finishtime;
    private String student;
    private Integer id;
    private String teacher;
    private byte[] metails;
    private String partment;

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    private Integer badge;

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public CompetitionEntity() {
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public byte[] getMetails() {
        return metails;
    }

    public void setMetails(byte[] metails) {
        this.metails = metails;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionEntity(String name, String grade, String level, Integer status, String finishtime, String student, String teacher, byte[] metails) {
        this.name = name;
        this.grade = grade;
        this.level = level;
        this.status = status;
        this.finishtime = finishtime;
        this.student = student;
        this.teacher = teacher;
        this.metails = metails;
    }

    public CompetitionEntity(String name, String grade, String level, Integer status, String finishtime, String student, String teacher, String partment) {
        this.name = name;
        this.grade = grade;
        this.level = level;
        this.status = status;
        this.finishtime = finishtime;
        this.student = student;
        this.teacher = teacher;
        this.partment = partment;
    }
}
