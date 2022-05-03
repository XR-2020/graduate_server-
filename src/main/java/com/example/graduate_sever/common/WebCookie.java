package com.example.graduate_sever.common;

import java.util.ArrayList;
import java.util.List;

public class WebCookie {
    public static String cookie="JSESSIONID=94C074D8D594C9B5E724FA683AA31DC5";
    public static String date;

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        WebCookie.date = date;
    }

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        WebCookie.cookie = cookie;
    }
}
