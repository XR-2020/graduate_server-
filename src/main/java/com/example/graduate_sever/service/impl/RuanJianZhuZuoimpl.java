package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.RuanJianZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.RuanJianZhuZuoService;
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

@Service("RuanJianZhuZuoService")
public class RuanJianZhuZuoimpl implements RuanJianZhuZuoService {
    @Autowired
    private RuanJianZhuZuoMapper mapper;
    @Override
    public ResVO getAllRuanJianZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getAllRuanJianZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchRuanJianZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getSearchRuanJianZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteRuanJianZhuZuo(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneRuanJianZhuZuo(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneRuanJianZhuZuo(Integer id) {
        mapper.deleteOneRuanJianZhuZuo(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getRuanJianZhuZuoDetial(Integer id) {
        return new JsonBean(200,"",mapper.getRuanJianZhuZuoDetail(id));
    }

    @Override
    public JsonBean insertRuanJianZhuZuo(HeBingEntity entity,Integer[] people) {
        mapper.insertRuanJianZhuZuo(entity);
        for (Integer ach_id:people) {
            mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(ach_id,entity.getId(),11));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void RuanJianZhuZuoCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            for(int i=0;i<parent.length;i+=10){
//                System.out.println("id="+entity[i]);
                HeBingEntity ruanJianZhuZuoEntity=new HeBingEntity(1,parent[i+2],parent[i+7],parent[i+1]);
                mapper.insertRuanJianZhuZuo(ruanJianZhuZuoEntity);
                //设置小眼睛参数
                List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                viewparams.add(new BasicNameValuePair("tb",td));
                viewparams.add(new BasicNameValuePair("id",parent[i]));
                //       System.out.println(viewparams.toString());
                UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                view.setEntity(viewformEntity);
                //获取小眼睛内容
                String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                System.out.println("第一完成人工号="+parent[i+6]);
//                System.out.println("____________________________________________");
//                for (String b:people) {
//                    System.out.println(b);
//                }
//                System.out.println("____________________________________________")
                for(int j=5;j<people.length;j+=4){
                    System.out.println("参与人id="+people[j]);
                    mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(people[j]),ruanJianZhuZuoEntity.getId(),1));
                }
                //            System.out.println(chanXueYanEntity.getId());
                //添加第一完成人
                mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(parent[i+6]),ruanJianZhuZuoEntity.getId(),1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
