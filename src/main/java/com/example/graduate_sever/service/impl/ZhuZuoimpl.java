package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuZuoService;
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

@Service("ZhuZuoService")
public class ZhuZuoimpl implements ZhuZuoService {
    @Autowired
    private ZhuZuoMapper mapper;
    @Override
    public ResVO getAllZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getAllZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getSearchZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteZhuZuo(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneZhuZuo(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneZhuZuo(Integer id) {
        mapper.deleteOneZhuZuo(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getZhuZuoDetial(Integer id) {
        return new JsonBean(200,"",mapper.getZhuZuoDetail(id));
    }

    @Override
    public JsonBean insertZhuZuo(HeBingEntity entity,Integer[] people) {
        mapper.insertZhuZuo(entity);
        for (Integer ach_id:people) {
            mapper.insertZhuZuoParticipation(new ParticipationEntity(ach_id,entity.getId(),9));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void ZhuZuoCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            //设置除参与人外其他信息
            for(int i=0;i<parent.length;i+=11){
//                System.out.println("id="+entity[i]);
                ChanXueYanEntity chanXueYanEntity=new ChanXueYanEntity(1,formatter.format(date),parent[i+4],parent[i+3],parent[i+2],parent[i+1]);
                mapper.insertZhuZuo(chanXueYanEntity);
                //设置小眼睛参数
                List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                viewparams.add(new BasicNameValuePair("tb",td));
                viewparams.add(new BasicNameValuePair("id",parent[i]));
                //       System.out.println(viewparams.toString());
                UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                view.setEntity(viewformEntity);
                //获取小眼睛内容
                String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                System.out.println("第一完成人工号="+parent[i+8]);
//                System.out.println("____________________________________________");
//                for (String b:people) {
//                    System.out.println(b);
//                }
//                System.out.println("____________________________________________")
                for(int j=5;j<people.length;j+=4){
                    System.out.println("参与人id="+people[j]);
                    mapper.insertZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(people[j]),chanXueYanEntity.getId(),1));
                }
                //            System.out.println(chanXueYanEntity.getId());
                //添加第一完成人
                mapper.insertZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(parent[i+8]),chanXueYanEntity.getId(),1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
