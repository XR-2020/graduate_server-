package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.JiaoYuGuiHuaMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.JiaoYuGuiHuaXiangMu;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
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

@Service("JiaoYuGuiHuaService")
public class JiaoYuGuiHuaimpl implements JiaoYuGuiHuaService {
    @Autowired
    private JiaoYuGuiHuaMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getAllJiaoYuGuiHua(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getSearchJiaoYuGuiHua(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYuGuiHua(List<Integer> ids) {
        for (Integer id:ids) {
            mapper.deleteOneJiaoYuGuiHua(id);
            chanXueYanMapper.deletePeople(id,5);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYuGuiHua(Integer id) {
        mapper.deleteOneJiaoYuGuiHua(id);
        chanXueYanMapper.deletePeople(id,5);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYuGuiHuaDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJiaoYuGuiHuaDetail(id));
    }

    @Override
    public JsonBean insertJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity,Integer[] people) {
        mapper.insertJiaoYuGuiHua(entity);
        for (Integer ach_id:people) {
            mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(ach_id,entity.getId(),5));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity, Integer[] people) {
        int ref=mapper.shenBaoJiaoYuGuiHua(entity);
       if(ref!=0){
           for (Integer ach_id:people) {
               mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(ach_id,entity.getId(),5));
           }
       }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void JiaoYuGuiHuaCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
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
            String[] finishtime=doc.getElementsByAttributeValue("fd","结题时间").text().split("\\s+");
            String[] danwei=doc.getElementsByAttributeValue("fd","组织结题单位").text().split("\\s+");
            String[] level=doc.getElementsByAttributeValue("fd","项目级别").text().split("\\s+");
            String[] grade=doc.getElementsByAttributeValue("fd","结题等级").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                JiaoYuGuiHuaXiangMuEntity jiaoYuGuiHuaXiangMuEntity=new JiaoYuGuiHuaXiangMuEntity(1,finishtime[i],partment[i],name.get(i).text(),grade[i],level[i],danwei[i],Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertJiaoYuGuiHua(jiaoYuGuiHuaXiangMuEntity);
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
                        mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(Integer.parseInt(people[j]),jiaoYuGuiHuaXiangMuEntity.getId(),5));
                    }
                    //添加第一完成人
                    mapper.insertJiaoYuGuiHuaParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),jiaoYuGuiHuaXiangMuEntity.getId(),5));

                }else{
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Metails getJiaoYuGuiHuaMetails(Integer id) {
        return mapper.JiaoYuGuiHuaMetails(id);
    }

    @Override
    public List<TableData> waitingjiaoyuguihua(DTO dTO) {
        List<JiaoYuGuiHuaXiangMu> list=mapper.waitingjiaoyuguihua(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (JiaoYuGuiHuaXiangMu c:list) {
            tableData.add(new TableData(c,mapper.getJiaoYuGuiHuaDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passJiaoYuGuiHua(Integer id, Integer ispass) {
        return mapper.passJiaoYuGuiHua(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getJiaoYuGuiHuaDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getJiaoYuGuiHuaDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getJiaoYuGuiHuaDetailBadge(Integer id) {
        List<People> people=mapper.getJiaoYuGuiHuaDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editJiaoYuGuiHua(Integer id, String name, String finishtime, String partment, String danwei, String grade, String level, Integer[] people) {
        int ref=mapper.editJiaoYuGuiHua(id,name,finishtime,partment,danwei,grade,level);
        if(ref==1){
            chanXueYanMapper.deletePeople(id,5);
            for (Integer p:people) {
                chanXueYanMapper.editPeople(id,p,5);
            }
        }
        return ref;
    }
}
