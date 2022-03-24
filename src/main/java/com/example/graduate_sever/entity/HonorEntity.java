package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 荣誉称号实体
* */
public class HonorEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status=0;
    private String name;
    private String badge;
    private String level;
    private String finishtime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
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
}
