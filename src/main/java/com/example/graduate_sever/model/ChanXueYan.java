package com.example.graduate_sever.model;

import java.io.Serializable;

/*
* 产学研实体
* */

public class ChanXueYan implements Serializable{
    private static final long serialVersionUID=1L;
    private Integer id;
    private Integer status;
    private String finishtime;
    private String lianghua;
    private String wenhao;
    private String name;
    private String partment;
    private Integer badge;
    private String shenbao;
    private byte[] metails;

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

    public String getLianghua() {
        return lianghua;
    }

    public void setLianghua(String lianghua) {
        this.lianghua = lianghua;
    }

    public String getWenhao() {
        return wenhao;
    }

    public void setWenhao(String wenhao) {
        this.wenhao = wenhao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
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
