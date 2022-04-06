package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYuGuiHuaMapper;
import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
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

@Service("JiaoYuGuiHuaService")
public class JiaoYuGuiHuaimpl implements JiaoYuGuiHuaService {
    @Autowired
    private JiaoYuGuiHuaMapper mapper;
    @Override
    public ResVO getAllJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getAllJiaoYuGuiHua(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getSearchJiaoYuGuiHua(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYuGuiHua(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneJiaoYuGuiHua(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYuGuiHua(Integer id) {
        mapper.deleteOneJiaoYuGuiHua(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYuGuiHuaDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJiaoYuGuiHuaDetail(id));
    }

    @Override
    public JsonBean insertJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity,Integer[] people) {
        mapper.insertJiaoYuGuiHua(entity);
        for (Integer ach_id:people) {
            mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(ach_id,entity.getId(),5));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void JiaoYuGuiHuaCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
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
            for(int i=0;i<parent.length;i+=13){
//                System.out.println("id="+entity[i]);
                JiaoYuGuiHuaXiangMuEntity jiaoYuGuiHuaXiangMuEntity=new JiaoYuGuiHuaXiangMuEntity(1,parent[i+1],parent[i+10],parent[i+2],parent[i+5],parent[i+4],parent[i+3]);
                mapper.insertJiaoYuGuiHua(jiaoYuGuiHuaXiangMuEntity);
                //设置小眼睛参数
                List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                viewparams.add(new BasicNameValuePair("tb",td));
                viewparams.add(new BasicNameValuePair("id",parent[i]));
                //       System.out.println(viewparams.toString());
                UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                view.setEntity(viewformEntity);
                //获取小眼睛内容
                String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                System.out.println("第一完成人工号="+parent[i+9]);
//                System.out.println("____________________________________________");
//                for (String b:people) {
//                    System.out.println(b);
//                }
//                System.out.println("____________________________________________")
                for(int j=5;j<people.length;j+=4){
                    System.out.println("参与人id="+people[j]);
                    mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(Integer.parseInt(people[j]),jiaoYuGuiHuaXiangMuEntity.getId(),1));
                }
                //            System.out.println(chanXueYanEntity.getId());
                //添加第一完成人
                mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(Integer.parseInt(parent[i+9]),jiaoYuGuiHuaXiangMuEntity.getId(),1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
