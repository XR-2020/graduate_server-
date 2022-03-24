package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 评估中心项目实体
* */
public class PingGuZhongXinXiangGuanEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer status=0;
    private String finishtime;
    private String grade;
    private String partment;
    private String name;
    private Integer badge;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
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
