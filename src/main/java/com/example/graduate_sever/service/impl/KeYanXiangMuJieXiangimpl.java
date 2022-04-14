package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.KeYanXiangMuJieXiangMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.KeYanXiangMuJieXiang;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.KeYanXiangMuJieXiangService;
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

@Service("KeYanXiangMuJieXiangService")
public class KeYanXiangMuJieXiangimpl implements KeYanXiangMuJieXiangService {
    @Autowired
    private KeYanXiangMuJieXiangMapper mapper;
    @Override
    public ResVO getAllKeYanXiangMuJieXiang(DTO dTO) {
        List<List<Object>>data=mapper.getAllKeYanXiangMuJieXiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeYanXiangMuJieXiang(DTO dTO) {
        List<List<Object>>data=mapper.getSearchKeYanXiangMuJieXiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeYanXiangMuJieXiang(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneKeYanXiangMuJieXiang(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeYanXiangMuJieXiang(Integer id) {
        mapper.deleteOneKeYanXiangMuJieXiang(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeYanXiangMuJieXiangDetial(Integer id) {
        return new JsonBean(200,"",mapper.getKeYanXiangMuJieXiangDetail(id));
    }

    @Override
    public JsonBean insertKeYanXiangMuJieXiang(HeBingEntity entity,Integer[] people) {
       mapper.insertKeYanXiangMuJieXiang(entity);
           for (Integer ach_id:people) {
               mapper.insertKeYanXiangMuJieXiangParticipation(new ParticipationEntity(ach_id,entity.getId(),12));
       }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoKeYanXiangMuJieXiang(HeBingEntity entity, Integer[] people) {
        int ref=mapper.shenBaoKeYanXiangMuJieXiang(entity);
        if(ref!=0){
           for (Integer ach_id:people) {
               mapper.insertKeYanXiangMuJieXiangParticipation(new ParticipationEntity(ach_id,entity.getId(),12));
           }
       }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void KeYanXiangMuJieXiangCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
                HeBingEntity keYanXiangMuJieXiangEntity=new HeBingEntity(1,formatter.format(date),partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertKeYanXiangMuJieXiang(keYanXiangMuJieXiangEntity);
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
                    for(int j=5;j<people.length;j+=4){
                        System.out.println("参与人id="+people[j]);
                        mapper.insertKeYanXiangMuJieXiangParticipation(new ParticipationEntity(Integer.parseInt(people[j]),keYanXiangMuJieXiangEntity.getId(),12));
                    }
                    //添加第一完成人
                    mapper.insertKeYanXiangMuJieXiangParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),keYanXiangMuJieXiangEntity.getId(),12));

                }else{
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Metails getKeYanXiangMuJieXiangMetails(Integer id) {
        return mapper.KeYanXiangMuJieXiangMetails(id);
    }

    @Override
    public List<TableData> waitingkeyanxiangmujiexiang(DTO dTO) {
        List<KeYanXiangMuJieXiang> list=mapper.waitingkeyanxiangmujiexiang(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (KeYanXiangMuJieXiang c:list) {
            tableData.add(new TableData(c,mapper.getKeYanXiangMuJieXiangDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passKeYanXiangMuJieXiang(Integer id, Integer ispass) {
        return mapper.passKeYanXiangMuJieXiang(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getKeYanXiangMuJieXiangDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getKeYanXiangMuJieXiangDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getKeYanXiangMuJieXiangDetailBadge(Integer id) {
        List<People> people=mapper.getKeYanXiangMuJieXiangDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }
}
