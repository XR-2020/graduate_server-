package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.JiaoYanXiangMu;
import com.example.graduate_sever.model.MyShenBaoModel;
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
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;

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
    public JsonBean deleteJiaoYan(List<Integer> ids) {
        for (Integer id:ids) {
            mapper.deleteOneJiaoYan(id);
            chanXueYanMapper.deletePeople(id,2);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYan(Integer id) {
        mapper.deleteOneJiaoYan(id);
        chanXueYanMapper.deletePeople(id,2);
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
        //获取时间
        String date=WebCookie.getDate();
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
                JiaoYanXiangMuEntity jiaoYanXiangMuEntity=new JiaoYanXiangMuEntity(1,date,lianghua[i],wenhao[i],partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
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

    @Override
    public int passJiaoYan(Integer id, Integer ispass) {
        return mapper.passJiaoYan(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getJiaoYanXiangDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getJiaoYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getJiaoYanDetialBadge(Integer id) {
        List<People> people=mapper.getJiaoYanDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editJiaoYan(Integer id, String name, String finishtime, String partment, String lianghua, Integer[] people, String wenhao) {
        int ref=mapper.editJiaoYan(id,name,finishtime,partment,lianghua,wenhao);
        if(ref==1){
            chanXueYanMapper.deletePeople(id,2);
            for (Integer p:people) {
                chanXueYanMapper.editPeople(id,p,2);
            }
        }
        return ref;
    }
}
