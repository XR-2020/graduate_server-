package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 教研论文实体
* */

public class JiaoYanLunWenEntity implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private Integer status;
    private String finishtime;
    private String partment;
    private String name;
    private Integer badge;

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

    public JiaoYanLunWenEntity(Integer status, String finishtime, String partment, String name, Integer badge) {
        this.status = status;
        this.finishtime = finishtime;
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
