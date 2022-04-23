package com.example.graduate_sever.common;

import java.util.ArrayList;
import java.util.List;

public class WebCookie {
    public static String cookie="JSESSIONID=009FFBF2668C3651CDD14B77018C63A4";
    public static String date;
//    public static List<String> typeAll=new ArrayList<String>("科技处_著作","社科处_教育规划项目纵向结题","科技处_横向科研项目业绩表","","","","","");
//
//
//    public static List<String> getTypeAll() {
//        return typeAll;
//    }
//
//    public static void setTypeAll(List<String> typeAll) {
//        WebCookie.typeAll = typeAll;
//    }

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
