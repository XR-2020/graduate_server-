package com.example.graduate_sever.entity;

import java.io.Serializable;

/*
* 产学研实体
* */

public class ChanXueYanEntity implements Serializable{
    private static final long serialVersionUID=1L;
    private Integer status=0;
    private String finishtime;
    private String lianghua;
    private String wenhao;
    private String name;
    private String partment;

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

    @Override
    public String toString() {
        return "ChanXueYanEntity{" +
                "status=" + status +
                ", finishtime='" + finishtime + '\'' +
                ", lianghua='" + lianghua + '\'' +
                ", wenhao='" + wenhao + '\'' +
                ", name='" + name + '\'' +
                ", partment='" + partment + '\''  +
                '}';
    }
}
