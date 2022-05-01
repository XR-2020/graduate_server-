package com.example.graduate_sever.common.EditUO;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/*
* 社科处实体
* */

public class SheKeChuEditUO implements Serializable{
    private static final long serialVersionUID=1L;

    private Integer id;
    private Integer status;
    private String finishtime;
    private String name;
    private String partment;
    private Integer badge;
    private MultipartFile metails;
    private String jietidengji;
    private String xiangmujibie;
    private String jianglijibie;
    private String jianglidengji;
    private String huojiangleibie;
    private String chengguotype;
    private String danwei;
    private String shenbao;
    private String type;
    private Integer[] people;

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

    public MultipartFile getMetails() {
        return metails;
    }

    public void setMetails(MultipartFile metails) {
        this.metails = metails;
    }

    public String getJietidengji() {
        return jietidengji;
    }

    public void setJietidengji(String jietidengji) {
        this.jietidengji = jietidengji;
    }

    public String getXiangmujibie() {
        return xiangmujibie;
    }

    public void setXiangmujibie(String xiangmujibie) {
        this.xiangmujibie = xiangmujibie;
    }

    public String getJianglijibie() {
        return jianglijibie;
    }

    public void setJianglijibie(String jianglijibie) {
        this.jianglijibie = jianglijibie;
    }

    public String getJianglidengji() {
        return jianglidengji;
    }

    public void setJianglidengji(String jianglidengji) {
        this.jianglidengji = jianglidengji;
    }

    public String getHuojiangleibie() {
        return huojiangleibie;
    }

    public void setHuojiangleibie(String huojiangleibie) {
        this.huojiangleibie = huojiangleibie;
    }

    public String getChengguotype() {
        return chengguotype;
    }

    public void setChengguotype(String chengguotype) {
        this.chengguotype = chengguotype;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getShenbao() {
        return shenbao;
    }

    public void setShenbao(String shenbao) {
        this.shenbao = shenbao;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }
}
