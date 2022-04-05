package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Teacher;
import com.example.graduate_sever.service.ChanXueYanService;
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
import org.apache.http.NameValuePair;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ChanXueYanService")
public class ChanXueYanimpl implements ChanXueYanService {
    @Autowired
    private ChanXueYanMapper chanxueyanMapper;
    @Override
    public ResVO selectAll(DTO chanXueYanDTO) {
        List<List<Object>>data=chanxueyanMapper.getAll(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchChanXueYan(DTO chanXueYanDTO) {
        List<List<Object>>data=chanxueyanMapper.getSearchChanXueYan(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteChanXueYan(int[] ids) {
        for (int id:ids) {
            chanxueyanMapper.deleteOneChanXueYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneChanXueYan(Integer id) {
        chanxueyanMapper.deleteOneChanXueYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getChanXueYanDetail(Integer id) {
        return new JsonBean(200,"",chanxueyanMapper.getChanXueYanDetail(id));
    }

    @Override
    public JsonBean insertChanXueYan(ChanXueYanEntity entity, Integer[] people) {
        chanxueyanMapper.insertChanXueYan(entity);
        for (Integer badge:people) {
            chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(badge,entity.getId(),1));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public List<Object> getTeacherList() {
        return  chanxueyanMapper.getTeacherList();
    }

    @Override
    public void crawlerWebSite(String td, CloseableHttpClient httpClient,String cookie){
        Document doc=null;
        String content=null;
        Elements elements=null;
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // System.out.println(formatter.format(date).toString());
        //获取对应项目列表
        HttpPost list=new HttpPost("http://jx.zut.edu.cn/list.jsp");
        list.setHeader("Cookie",cookie);
        //设置请求地址的参数
        listparams.add(new BasicNameValuePair("tb",td));
        listparams.add(new BasicNameValuePair("pageNum","0"));
        listparams.add(new BasicNameValuePair("keyword",""));
        //获取小眼睛
        HttpPost view=new HttpPost("http://jx.zut.edu.cn/view.jsp");
        view.setHeader("Cookie",cookie);
        UrlEncodedFormEntity formEntity= null;
        try {
            formEntity = new UrlEncodedFormEntity(listparams,"utf-8");
            list.setEntity(formEntity);
            doc= Jsoup.parse(EntityUtils.toString(httpClient.execute(list).getEntity()));
//            for (String a:doc.getElementsByTag("span").text().split("\\s+")) {
//                System.out.println(a);
//            }
            String[] entity=doc.getElementsByTag("span").text().split("\\s+");
            System.out.println("entity.length"+entity.length);
            //设置除参与人外其他信息
            for(int i=0;i<entity.length;i+=11){
//                System.out.println("id="+entity[i]);
                ChanXueYanEntity chanXueYanEntity=new ChanXueYanEntity(1,formatter.format(date),entity[i+4],entity[i+3],entity[i+2],entity[i+1]);
                chanxueyanMapper.insertChanXueYan(chanXueYanEntity);
                //设置小眼睛参数
                List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                viewparams.add(new BasicNameValuePair("tb",td));
                viewparams.add(new BasicNameValuePair("id",entity[i]));
                System.out.println(viewparams.toString());
                UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                view.setEntity(viewformEntity);
                //获取小眼睛内容
                String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                System.out.println("第一完成人工号="+entity[i+8]);
//                System.out.println("____________________________________________");
//                for (String b:people) {
//                    System.out.println(b);
//                }
//                System.out.println("____________________________________________");
                for(int j=5;j<people.length;j+=4){
                    System.out.println("参与人id="+people[j]);
                    chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(Integer.parseInt(people[j]),chanXueYanEntity.getId(),1));
                }
                //            System.out.println(chanXueYanEntity.getId());
                //添加第一完成人
                chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(Integer.parseInt(entity[i+8]),chanXueYanEntity.getId(),1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
