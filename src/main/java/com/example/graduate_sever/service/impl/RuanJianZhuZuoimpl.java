package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.RuanJianZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.RuanJianZhuZuoQuan;
import com.example.graduate_sever.service.RuanJianZhuZuoService;
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

@Service("RuanJianZhuZuoService")
public class RuanJianZhuZuoimpl implements RuanJianZhuZuoService {
    @Autowired
    private RuanJianZhuZuoMapper mapper;
    @Override
    public ResVO getAllRuanJianZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getAllRuanJianZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchRuanJianZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getSearchRuanJianZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteRuanJianZhuZuo(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneRuanJianZhuZuo(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneRuanJianZhuZuo(Integer id) {
        mapper.deleteOneRuanJianZhuZuo(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getRuanJianZhuZuoDetial(Integer id) {
        return new JsonBean(200,"",mapper.getRuanJianZhuZuoDetail(id));
    }

    @Override
    public JsonBean insertRuanJianZhuZuo(HeBingEntity entity,Integer[] people) {
        mapper.insertRuanJianZhuZuo(entity);
        for (Integer ach_id:people) {
            mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(ach_id,entity.getId(),11));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoRuanJianZhuZuo(HeBingEntity entity, Integer[] people) {
        int ref=mapper.shenBaoRuanJianZhuZuo(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(ach_id,entity.getId(),11));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public void RuanJianZhuZuoCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            Elements name=doc.getElementsByAttributeValue("fd","名称");
            String[] partment=doc.getElementsByAttributeValue("fd","部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            String[] finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                HeBingEntity ruanJianZhuZuoEntity=new HeBingEntity(1,finishtime[i],partment[i],name.get(i).text(),Integer.parseInt(firstpeople[i]));
                int ref=mapper.insertRuanJianZhuZuo(ruanJianZhuZuoEntity);
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
                        mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(people[j]),ruanJianZhuZuoEntity.getId(),11));
                    }
                    //添加第一完成人
                    mapper.insertRuanJianZhuZuoParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),ruanJianZhuZuoEntity.getId(),11));

                }else{
                    continue;
                }
               }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TableData> waitingruanjianzhuzuo(DTO dTO) {
        List<RuanJianZhuZuoQuan> list=mapper.waitingruanjianzhuzuo(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (RuanJianZhuZuoQuan c:list) {
            tableData.add(new TableData(c,mapper.getRuanJianZhuZuoDetail(c.getId())));
        }
        return tableData;
    }
}
