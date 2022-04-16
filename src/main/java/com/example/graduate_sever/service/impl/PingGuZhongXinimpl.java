package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.model.PingGuZhongXinXiangGuan;
import com.example.graduate_sever.service.PingGuZhongXinService;
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

@Service("PingGuZhongXinService")
public class PingGuZhongXinimpl implements PingGuZhongXinService {
    @Autowired
    private PingGuZhongXinMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllPingGuZhongXin(DTO dTO) {
        List<List<Object>>data=mapper.getAllPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchPingGuZhongXin(DTO dTO) {
        List<List<Object>>data=mapper.getSearchPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deletePingGuZhongXin(int[] ids) {
        for (int id:ids) {
            mapper.deleteOnePingGuZhongXin(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOnePingGuZhongXin(Integer id) {
        mapper.deleteOnePingGuZhongXin(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getPingGuZhongXinDetial(Integer id) {
        return new JsonBean(200,"",mapper.getPingGuZhongXinDetail(id));
    }

    @Override
    public JsonBean insertPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people) {
        mapper.insertPingGuZhongXin(entity);
        for (Integer ach_id:people) {
            mapper.insertPingGuZhongXinParticipation(new ParticipationEntity(ach_id,entity.getId(),4));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity, Integer[] people) {
        int ref=mapper.shenBaoPingGuZhongXin(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertPingGuZhongXinParticipation(new ParticipationEntity(ach_id,entity.getId(),4));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void PingGuZhongXinCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
            String[] grade=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                PingGuZhongXinXiangGuanEntity pingGuZhongXinXiangGuanEntity=new PingGuZhongXinXiangGuanEntity(1,formatter.format(date),grade[i],partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertPingGuZhongXin(pingGuZhongXinXiangGuanEntity);
                if(ref!=0){
                    //添加第一完成人
                    mapper.insertPingGuZhongXinParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),pingGuZhongXinXiangGuanEntity.getId(),4));
                }else{
                    continue;
                }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Metails getPingGuZhongXinMetails(Integer id) {
        return mapper.PingGuZhongXinMetails(id);
    }

    @Override
    public List<TableData> waitingpingguzhongxin(DTO dTO) {
        List<PingGuZhongXinXiangGuan> list=mapper.waitingpingguzhongxin(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (PingGuZhongXinXiangGuan c:list) {
            tableData.add(new TableData(c,mapper.getPingGuZhongXinDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passPingGuZhongXin(Integer id, Integer ispass) {
        return mapper.passPingGuZhongXin(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getPingGuZhongXinDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getPingGuZhongXinDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getPingGuZhongXinDetailBadge(Integer id) {
        List<People> people=mapper.getPingGuZhongXinDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editPingGuZhongXin(Integer id, String name, String finishtime, String partment, Integer[] people, String grade) {
        int ref=mapper.editPingGuZhongXin(id,name,finishtime,partment,grade);
        if(ref==1){
            chanXueYanMapper.deletePeople(id,4);
            for (Integer p:people) {
                chanXueYanMapper.editPeople(id,p,4);
            }
        }
        return ref;
    }
}
