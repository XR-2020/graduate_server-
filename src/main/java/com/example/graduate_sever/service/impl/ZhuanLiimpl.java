package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ZhuanLiMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.model.ZhuanLi;
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
import java.util.ArrayList;
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
        mapper.insertZhuanLi(entity);
        Integer ach_id=entity.getId();
        for (Integer id:people) {
            mapper.insertZhuanLiParticipation(new ParticipationEntity(id,ach_id,6));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoZhuanLi(HeBingEntity entity, Integer[] people) {
        int ref=mapper.shenBaoZhuanLi(entity);
        if(ref!=0){
            Integer ach_id=entity.getId();
            for (Integer id:people) {
                mapper.insertZhuanLiParticipation(new ParticipationEntity(id,ach_id,6));
            }
        }
        return new JsonBean(200,"",ref);
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
            Elements name=doc.getElementsByAttributeValue("fd","名称");
            String[] partment=doc.getElementsByAttributeValue("fd","部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            String[] finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
//                System.out.println("id="+entity[i]);
                HeBingEntity zhuanLiEntity=new HeBingEntity(1,finishtime[i],partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertZhuanLi(zhuanLiEntity);
                if(ref!=0){
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Metails getZhuanLiMetails(Integer id) {
        return mapper.ZhuanLiMetails(id);
    }

    @Override
    public List<TableData> waitingzhuanli(DTO dTO) {
        List<ZhuanLi> list=mapper.waitingzhuanli(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (ZhuanLi c:list) {
            tableData.add(new TableData(c,mapper.getZhuanLiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passZhuanLi(Integer id, Integer ispass) {
        return mapper.passZhuanLi(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getZhuanLiDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getZhuanLiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getZhuanLiDetailBadge(Integer id) {
        List<People> people=mapper.getZhuanLiDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }
}
