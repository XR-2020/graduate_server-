package com.example.graduate_sever.entity;

import java.io.Serializable;
import java.util.Arrays;

/*
* 新系统实体
* */

public class NewSystemEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;
    private Integer status;
    private String finishtime;
    private String partment;
    private String name;
    private Integer badge;
    private byte[] metails;
    private Integer shenbao;
    private String type;

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

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

    public NewSystemEntity(Integer status, String finishtime, String partment, String name, Integer shenbao, byte[] metails, String type) {
        this.status = status;
        this.finishtime = finishtime;
        this.partment = partment;
        this.name = name;
        this.shenbao = shenbao;
        this.metails = metails;
        this.type = type;
    }

    public NewSystemEntity(Integer status, String finishtime, String partment, String name, Integer badge, String type) {
        this.status = status;
        this.finishtime = finishtime;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
        this.type = type;
    }
}
