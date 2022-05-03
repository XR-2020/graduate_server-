package com.example.graduate_sever.common;


import org.springframework.beans.factory.annotation.Value;

public class WebConfig {

    @Value("${username}")
    public static String username;
    @Value("${password}")
    public static String password;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        WebConfig.username = username;
    }

    public static void setPassword(String password) {
        WebConfig.password = password;
    }

    public static String getPassword() {
        return password;
    }

}
