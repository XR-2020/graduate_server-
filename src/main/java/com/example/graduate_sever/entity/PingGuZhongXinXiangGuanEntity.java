package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 评估中心项目实体
* */
public class PingGuZhongXinXiangGuanEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status;
    private String finishtime;
    private String grade;
    private String partment;
    private String name;
    private Integer id;
    private Integer badge;
    private Integer shenbao;
    private byte[] metails;

    public byte[] getMetails() {
        return metails;
    }

    public void setMetails(byte[] metails) {
        this.metails = metails;
    }

    public PingGuZhongXinXiangGuanEntity(Integer status, String finishtime, String grade, String partment, String name, Integer badge, Integer shenbao, byte[] metails) {
        this.status = status;
        this.finishtime = finishtime;
        this.grade = grade;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
        this.shenbao = shenbao;
        this.metails = metails;
    }

    public PingGuZhongXinXiangGuanEntity(Integer status, String finishtime, String grade, String partment, String name, Integer badge, Integer shenbao) {
        this.status = status;
        this.finishtime = finishtime;
        this.grade = grade;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
        this.shenbao = shenbao;
    }

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
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

    public PingGuZhongXinXiangGuanEntity(Integer status, String finishtime, String grade, String partment, String name, Integer badge) {
        this.status = status;
        this.finishtime = finishtime;
        this.grade = grade;
        this.partment = partment;
        this.name = name;
        this.badge = badge;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
}
