package com.example.graduate_sever.common;

import com.example.graduate_sever.service.ChanXueYanService;
import com.example.graduate_sever.service.KeYanLunWenService;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WebSiteDateConfig {
    @Autowired
    public static KeYanLunWenService keYanLunWenService;
    public static void WebSiteDateConfig(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String cookie= WebCookie.getCookie();
        //获取对应项目列表
        HttpPost list=new HttpPost("http://jx.zut.edu.cn/list.jsp");
        list.setHeader("Cookie",cookie);
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        listparams.add(new BasicNameValuePair("tb","教务处-实践科_立项"));
        listparams.add(new BasicNameValuePair("pageNum","0"));
        listparams.add(new BasicNameValuePair("keyword",""));
        UrlEncodedFormEntity formEntity= null;
        try {
            formEntity = new UrlEncodedFormEntity(listparams,"utf-8");
            list.setEntity(formEntity);
            Document doc= Jsoup.parse(EntityUtils.toString(httpClient.execute(list).getEntity()));
            Elements finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间");
            String date=finishtime.get(0).text().substring(0,5)+"1.1";
            System.out.println(date);
            WebCookie.setDate(date);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
