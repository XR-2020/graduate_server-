package com.example.graduate_sever.common.UO;

import java.io.Serializable;
import java.util.Arrays;

/*
* 产学研实体
* */

public class SheKeChuUO implements Serializable{
    private static final long serialVersionUID=1L;
    private String name;
    private String partment;
    private String finishtime;
    private Integer[] people;
    private Integer role;
    private Integer shenbao;
    private String path;
    private String chengguotype;
    private String shenbaotype;
    private String jietidengji;
    private String xiangmujibie;
    private String jianglijibie;
    private String jianglidengji;
    private String huojiangleibie;
    private String danwei;

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
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

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChengguotype() {
        return chengguotype;
    }

    public void setChengguotype(String chengguotype) {
        this.chengguotype = chengguotype;
    }

    public String getShenbaotype() {
        return shenbaotype;
    }

    public void setShenbaotype(String shenbaotype) {
        this.shenbaotype = shenbaotype;
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
}
