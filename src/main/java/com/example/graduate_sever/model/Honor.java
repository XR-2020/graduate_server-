package com.example.graduate_sever.model;

import java.io.Serializable;

/*
* 荣誉称号实体
* */
public class Honor implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;
    private Integer status;
    private String name;
    private String level;
    private String finishtime;
    private Integer badge;
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
