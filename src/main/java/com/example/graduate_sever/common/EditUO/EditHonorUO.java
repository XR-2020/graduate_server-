package com.example.graduate_sever.common.EditUO;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Arrays;

/*
* 荣誉称号实体
* */
public class EditHonorUO implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String level;
    private String finishtime;
    private String teacher;
    private Integer shenbao;
    private Integer id;
    private MultipartFile metails;
    private Integer status;
    private String partment;

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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getShenbao() {
        return shenbao;
    }

    public void setShenbao(Integer shenbao) {
        this.shenbao = shenbao;
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

    public String getPartment() {
        return partment;
    }

    public void setPartment(String partment) {
        this.partment = partment;
    }
}
