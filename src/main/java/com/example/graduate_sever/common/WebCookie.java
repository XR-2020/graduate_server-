package com.example.graduate_sever.common;

public class WebCookie {
    public static String cookie;
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
