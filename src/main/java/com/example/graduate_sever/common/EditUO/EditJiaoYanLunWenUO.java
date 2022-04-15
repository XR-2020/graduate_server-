package com.example.graduate_sever.common.EditUO;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Arrays;

/*
* 教研论文实体
* */

public class EditJiaoYanLunWenUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String finishtime;
    private String partment;
    private Integer[] people;
    private Integer shenbao;
    private Integer badge;
    private Integer id;
    private MultipartFile metails;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer[] getPeople() {
        return people;
    }

    public void setPeople(Integer[] people) {
        this.people = people;
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

    public MultipartFile getMetails() {
        return metails;
    }

    public void setMetails(MultipartFile metails) {
        this.metails = metails;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
