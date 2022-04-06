package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import com.example.graduate_sever.service.PingGuZhongXinService;
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

@Service("PingGuZhongXinService")
public class PingGuZhongXinimpl implements PingGuZhongXinService {
    @Autowired
    private PingGuZhongXinMapper mapper;
    @Override
    public ResVO getAllPingGuZhongXin(DTO dTO) {
        List<List<Object>>data=mapper.getAllPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchPingGuZhongXin(DTO dTO) {
        List<List<Object>>data=mapper.getSearchPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deletePingGuZhongXin(int[] ids) {
        for (int id:ids) {
            mapper.deleteOnePingGuZhongXin(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOnePingGuZhongXin(Integer id) {
        mapper.deleteOnePingGuZhongXin(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getPingGuZhongXinDetial(Integer id) {
        return new JsonBean(200,"",mapper.getPingGuZhongXinDetail(id));
    }

    @Override
    public JsonBean insertPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people) {
        mapper.insertPingGuZhongXin(entity);
        for (Integer ach_id:people) {
            mapper.insertPingGuZhongXinParticipation(new ParticipationEntity(ach_id,entity.getId(),4));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void PingGuZhongXinCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            for (String a:parent) {
                System.out.println(a);
            }
            System.out.println("entity.length"+parent.length);
            //设置除参与人外其他信息
            for(int i=0;i<parent.length;i+=9){
//                System.out.println("id="+entity[i]);
                PingGuZhongXinXiangGuanEntity pingGuZhongXinXiangGuanEntity=new PingGuZhongXinXiangGuanEntity(1,formatter.format(date),parent[i+2],parent[i+7],parent[i+1]);
                mapper.insertPingGuZhongXin(pingGuZhongXinXiangGuanEntity);
                //添加第一完成人
                mapper.insertPingGuZhongXinParticipation(new ParticipationEntity(Integer.parseInt(parent[i+6]),pingGuZhongXinXiangGuanEntity.getId(),4));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
