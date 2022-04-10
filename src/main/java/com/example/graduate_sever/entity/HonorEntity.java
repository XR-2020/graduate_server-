package com.example.graduate_sever.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/*
* 荣誉称号实体
* */
public class HonorEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status;
    private String name;
    private String level;
    private String finishtime;
    private String partment;
    private Integer id;
    private Integer badge;
    private byte[] metails;

    public byte[] getMetails() {
        return metails;
    }

    public void setMetails(byte[] metails) {
        this.metails = metails;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HonorEntity(Integer status, String name, String level, String finishtime, String partment, Integer badge) {
        this.status = status;
        this.name = name;
        this.level = level;
        this.finishtime = finishtime;
        this.partment = partment;
        this.badge = badge;
    }

    public HonorEntity(Integer status, String name, String level, String finishtime, String partment, Integer badge, byte[] metails) {
        this.status = status;
        this.name = name;
        this.level = level;
        this.finishtime = finishtime;
        this.partment = partment;
        this.badge = badge;
        this.metails = metails;
    }

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
