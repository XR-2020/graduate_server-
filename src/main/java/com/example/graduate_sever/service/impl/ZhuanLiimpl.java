package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.Dao.ZhuanLiMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.PingGuZhongXinService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ZhuanLiService")
public class ZhuanLiimpl implements ZhuanLiService {
    @Autowired
    private ZhuanLiMapper mapper;
    @Override
    public ResVO getAllZhuanLi(DTO dTO) {
        List<List<Object>>data=mapper.getAllZhuanLi(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchZhuanLi(DTO dTO) {
        List<List<Object>>data=mapper.getSearchZhuanLi(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteZhuanLi(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneZhuanLi(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneZhuanLi(Integer id) {
        mapper.deleteOneZhuanLi(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getZhuanLiDetial(Integer id) {
        return new JsonBean(200,"",mapper.getZhuanLiDetail(id));
    }

    @Override
    public JsonBean insertZhuanLi(HeBingEntity entity,Integer[] people) {
        Integer ach_id=entity.getId();
        for (Integer id:people) {
            mapper.insertZhuanLiParticipation(new ParticipationEntity(id,ach_id,6));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void ZhuanLiCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        //获取当前时间
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
            String[] name=doc.getElementsByAttributeValue("fd","名称").text().split("\\s+");
            String[] partment=doc.getElementsByAttributeValue("fd","部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            String[] finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
//                System.out.println("id="+entity[i]);
                HeBingEntity zhuanLiEntity=new HeBingEntity(1,finishtime[i+2],partment[i+6],name[i+1]);
                mapper.insertZhuanLi(zhuanLiEntity);
                //设置小眼睛参数
                List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                viewparams.add(new BasicNameValuePair("tb",td));
                viewparams.add(new BasicNameValuePair("id",ids[i]));
                UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                view.setEntity(viewformEntity);
                //获取小眼睛内容
                String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                for(int j=5;j<people.length;j+=4){
                    mapper.insertZhuanLiParticipation(new ParticipationEntity(Integer.parseInt(people[j]),zhuanLiEntity.getId(),6));
                }
                //添加第一完成人
                mapper.insertZhuanLiParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),zhuanLiEntity.getId(),6));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
