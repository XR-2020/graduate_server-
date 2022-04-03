package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 科研项目结项实体
* */

public class HeBingEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status;
    private String finishtime;
    private String partment;
    private String name;

    public HeBingEntity(Integer status, String finishtime, String partment, String name) {
        this.status = status;
        this.finishtime = finishtime;
        this.partment = partment;
        this.name = name;
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
