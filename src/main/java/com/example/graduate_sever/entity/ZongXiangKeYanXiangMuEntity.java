package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 纵向科研项目实体
* */
public class ZongXiangKeYanXiangMuEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status;
    private String finishtime;
    private String level;
    private String type;
    private String lixiang;
    private String partment;
    private String name;
    private Integer id;
    private Integer badge;
    private byte[] metails;

    public byte[] getMetails() {
        return metails;
    }

    public void setMetails(byte[] metails) {
        this.metails = metails;
    }

    public ZongXiangKeYanXiangMuEntity(Integer status, String finishtime, String level, String type, String lixiang, String partment, String name, Integer badge, byte[] metails) {
        this.status = status;
        this.finishtime = finishtime;
        this.level = level;
        this.type = type;
        this.lixiang = lixiang;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
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

    public ZongXiangKeYanXiangMuEntity(Integer status, String finishtime, String level, String type, String lixiang, String partment, String name, Integer badge) {
        this.status = status;
        this.finishtime = finishtime;
        this.level = level;
        this.type = type;
        this.lixiang = lixiang;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
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
}
