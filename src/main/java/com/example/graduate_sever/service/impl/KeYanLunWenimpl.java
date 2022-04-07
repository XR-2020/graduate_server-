package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.KeYanLunWenMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.KeYanLunWenService;
import com.example.graduate_sever.service.ZhuZuoService;
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

@Service("KeYanLunWenService")
public class KeYanLunWenimpl implements KeYanLunWenService {
    @Autowired
    private KeYanLunWenMapper mapper;
    @Override
    public ResVO getAllKeYanLunWen(DTO dTO) {
        List<List<Object>>data=mapper.getAllKeYanLunWen(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeYanLunWen(DTO dTO) {
        List<List<Object>>data=mapper.getSearchKeYanLunWen(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeYanLunWen(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneKeYanLunWen(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeYanLunWen(Integer id) {
        mapper.deleteOneKeYanLunWen(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeYanLunWenDetial(Integer id) {
        return new JsonBean(200,"",mapper.getKeYanLunWenDetail(id));
    }

    @Override
    public JsonBean insertKeYanLunWen(HeBingEntity entity,Integer[] people) {
        mapper.insertKeYanLunWen(entity);
        for (Integer ach_id:people) {
            mapper.insertKeYanLunWenParticipation(new ParticipationEntity(ach_id,entity.getId(),10));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public void KeYanLunWenCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            String[] finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
//                System.out.println("id="+entity[i]);
                HeBingEntity keYanLunWenEntity=new HeBingEntity(1,finishtime[i],partment[i],name.get(i).text());
                int ref=mapper.insertKeYanLunWen(keYanLunWenEntity);
                if(ref!=0){
                    //添加第一完成人
                    mapper.insertKeYanLunWenParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),keYanLunWenEntity.getId(),10));
                }else{
                    continue;
                }
               }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
