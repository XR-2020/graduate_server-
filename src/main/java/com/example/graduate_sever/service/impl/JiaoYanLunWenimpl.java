package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanLunWenMapper;
import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("JiaoYanLunWenMuService")
public class JiaoYanLunWenimpl implements JiaoYanLunWenMuService {
    @Autowired
    private JiaoYanLunWenMapper jiaoYanLunWenMapper;
    @Override
    public ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanLunWenMapper.getAllJiaoYanLunWen(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanLunWenMapper.getSearchJiaoYanLunWen(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYanLunWen(int[] ids) {
        for (int id:ids) {
            jiaoYanLunWenMapper.deleteOneJiaoYanLunWen(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYanLunWen(Integer id) {
        jiaoYanLunWenMapper.deleteOneJiaoYanLunWen(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYanLunWenDetial(Integer id) {
        return new JsonBean(200,"",jiaoYanLunWenMapper.getJiaoYanLunWenDetail(id));
    }

    @Override
    public JsonBean insertJiaoYanLunWen(JiaoYanLunWenEntity entity,Integer[] people) {
        jiaoYanLunWenMapper.insertJiaoYanLunWen(entity);
        for (Integer ach_id:people) {
            jiaoYanLunWenMapper.insertJiaoYanLunWenParticipation(new ParticipationEntity(ach_id,entity.getId(),3));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void JiaoYanLunWenCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println(formatter.format(date).toString());
        //设置请求地址的参数
        listparams.add(new BasicNameValuePair("tb",td));
        listparams.add(new BasicNameValuePair("pageNum","0"));
        listparams.add(new BasicNameValuePair("keyword",""));
        UrlEncodedFormEntity formEntity= null;
        try {
            formEntity = new UrlEncodedFormEntity(listparams,"utf-8");
            list.setEntity(formEntity);
//            for (String a:doc.getElementsByTag("span").text().split("\\s+")) {
//                System.out.println(a);
//            }
            String[] parent= Jsoup.parse(EntityUtils.toString(httpClient.execute(list).getEntity())).getElementsByTag("span").text().split("\\s+");
            System.out.println("entity.length"+parent.length);
           //添加教研论文
            for(int i=0;i<parent.length;i+=10){
//                System.out.println("id="+entity[i]);
                JiaoYanLunWenEntity jiaoYanLunWenEntity=new JiaoYanLunWenEntity(1,formatter.format(date),parent[i+7],parent[i+1]);
                jiaoYanLunWenMapper.insertJiaoYanLunWen(jiaoYanLunWenEntity);
                jiaoYanLunWenMapper.insertJiaoYanLunWenParticipation(new ParticipationEntity(Integer.parseInt(parent[i+6]),jiaoYanLunWenEntity.getId(),3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
