package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 教师实体
* */
public class TeacherEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer badge;
    private Integer role;
    private String name;
    private String password;

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
