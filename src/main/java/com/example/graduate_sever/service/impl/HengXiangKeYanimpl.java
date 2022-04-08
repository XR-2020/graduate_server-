package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.ZhuanLiMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("HengXiangKeYanService")
public class HengXiangKeYanimpl implements HengXiangKeYanService {
    @Autowired
    private HengXiangKeYanMapper mapper;
    @Override
    public ResVO getAllHengXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getAllHengXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchHengXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getSearchHengXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteHengXiangKeYan(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneHengXiangKeYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneHengXiangKeYan(Integer id) {
        mapper.deleteOneHengXiangKeYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getHengXiangKeYanDetial(Integer id) {
        return new JsonBean(200,"",mapper.getHengXiangKeYanDetail(id));
    }

    @Override
    public JsonBean insertHengXiangKeYan(HeBingEntity entity,Integer[] people) {
        mapper.insertHengXiangKeYan(entity);
        Integer ach_id=entity.getId();
        for (Integer id:people) {
            mapper.insertHengXiangKeYanParticipation(new ParticipationEntity(id,ach_id,7));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoHengXiangKeYan(HeBingEntity entity, Integer[] people) {
        int ref=mapper.shenBaoHengXiangKeYan(entity);
       if(ref!=0){
           Integer ach_id=entity.getId();
           for (Integer id:people) {
               mapper.insertHengXiangKeYanParticipation(new ParticipationEntity(id,ach_id,7));
           }
       }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void HengXiangKeYanCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            Document doc=Jsoup.parse(EntityUtils.toString(httpClient.execute(list).getEntity()));
            String[] ids=doc.getElementsByAttributeValue("fd","序号").text().split("\\s+");
            Elements name=doc.getElementsByAttributeValue("fd","名称");
            String[] partment=doc.getElementsByAttributeValue("fd","部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                HeBingEntity heBingEntity=new HeBingEntity(1,formatter.format(date),partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
               int ref=mapper.insertHengXiangKeYan(heBingEntity);
               if(ref!=0){
                   //设置小眼睛参数
                   List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                   viewparams.add(new BasicNameValuePair("tb",td));
                   viewparams.add(new BasicNameValuePair("id",ids[i]));
                   //       System.out.println(viewparams.toString());
                   UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                   view.setEntity(viewformEntity);
                   //获取小眼睛内容
                   String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                   System.out.println("第一完成人工号="+firstpeople[i]);
                   for(int j=5;j<people.length;j+=4){
                       System.out.println("参与人id="+people[j]);
                       mapper.insertHengXiangKeYanParticipation(new ParticipationEntity(Integer.parseInt(people[j]),heBingEntity.getId(),7));
                   }
                   //添加第一完成人
                   mapper.insertHengXiangKeYanParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),heBingEntity.getId(),7));

               }else{
                   continue;
               }
               }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
