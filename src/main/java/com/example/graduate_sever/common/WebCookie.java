package com.example.graduate_sever.common;

public class WebCookie {
    public static String cookie;

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        WebCookie.cookie = cookie;
    }
}
