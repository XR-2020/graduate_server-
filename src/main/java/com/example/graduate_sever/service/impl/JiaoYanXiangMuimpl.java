package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.JiaoYanXiangMu;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
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

@Service("JiaoYanXiangMuService")
public class JiaoYanXiangMuimpl implements JiaoYanXiangMuService {
    @Autowired
    private JiaoYanXiangMuMapper mapper;
    @Override
    public ResVO getAllJiaoYan(DTO dTO) {
        List<List<Object>>data=mapper.getAllJiaoYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYan(DTO dTO) {
        List<List<Object>>data=mapper.getSearchJiaoYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYan(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneJiaoYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYan(Integer id) {
        mapper.deleteOneJiaoYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYanDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJiaoYanDetail(id));
    }

    @Override
    public JsonBean insertJiaoYan(JiaoYanXiangMuEntity entity,Integer[] people) {
        mapper.insertJiaoYanXiangMu(entity);
        for (Integer ach_id:people) {
            mapper.insertJiaoYanXiangMuParticipation(new ParticipationEntity(ach_id,entity.getId(),2));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoJiaoYan(JiaoYanXiangMuEntity entity, Integer[] people) {
      int ref=mapper.shenBaoJiaoYanXiangMu(entity);
      if(ref!=0){
          for (Integer ach_id:people) {
              mapper.insertJiaoYanXiangMuParticipation(new ParticipationEntity(ach_id,entity.getId(),2));
          }
      }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void JiaoYanXiangMuCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            Elements name=doc.getElementsByAttributeValue("fd","项目名称");
            String[] partment=doc.getElementsByAttributeValue("fd","部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            String[] lianghua=doc.getElementsByAttributeValue("fd","量化依据").text().split("\\s+");
            String[] wenhao=doc.getElementsByAttributeValue("fd","立项文号").text().split("\\s+");

            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                JiaoYanXiangMuEntity jiaoYanXiangMuEntity=new JiaoYanXiangMuEntity(1,formatter.format(date),lianghua[i],wenhao[i],partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertJiaoYanXiangMu(jiaoYanXiangMuEntity);
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
                        mapper.insertJiaoYanXiangMuParticipation(new ParticipationEntity(Integer.parseInt(people[j]),jiaoYanXiangMuEntity.getId(),2));
                    }
                    //添加第一完成人
                    mapper.insertJiaoYanXiangMuParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),jiaoYanXiangMuEntity.getId(),2));

                }else{
                    continue;
                }
              }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TableData> waitingjiaoyanxiangmu(DTO dTO) {
        List<JiaoYanXiangMu> list=mapper.waitingjiaoyanxiangmu(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (JiaoYanXiangMu c:list) {
            tableData.add(new TableData(c,mapper.getJiaoYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public Metails getJiaoYanXiangMuMetails(Integer id) {
        return mapper.JiaoYanXiangMetails(id);
    }
}
