package com.example.graduate_sever.common;

import lombok.Data;

@Data
public class People {
    private Integer badge;
    private String name;

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
