package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.NewParticipationEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.entity.SheKeChuEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.model.Teacher;
import com.example.graduate_sever.service.ChanXueYanService;
import com.example.graduate_sever.service.SheKeChuService;
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("SheKeChuService")
public class SheKeChuimpl implements SheKeChuService {
    @Autowired
    private SheKeChuMapper mapper;
    @Autowired
    private NewSystemMapper newSystemMapper;

    @Override
    public ResVO getAllKeYanHuoJiang(SheKeDTO dTO) {
        List<List<Object>>data=mapper.getAllKeYanHuoJiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeYanHuoJiang(SheKeDTO dTO) {
        List<List<Object>>data=mapper.getSearchKeYanHuoJiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeYanHuoJiang(List<Integer> ids,String type) {
        for (int id:ids) {
            mapper.deleteOneKeYanHuoJiang(id,type);
            mapper.deleteAllPeople(id,type);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeYanHuoJiang(Integer id,String type) {
        mapper.deleteOneKeYanHuoJiang(id,type);
        mapper.deleteAllPeople(id,type);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeYanHuoJiangDetail(Integer id,String type) {
        return new JsonBean(200,"",mapper.getKeYanHuoJiangDetail(id,type));
    }

    @Override
    public JsonBean insertSheKeChu(SheKeChuUO uo) throws Exception {
        SheKeChuEntity element;
        Integer[] people=uo.getPeople();
        Integer role=uo.getRole();
        //读取证明材料
        File file=new File(uo.getPath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bin = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            while (bin.read(buffer) > 0) {
                bos.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bin.close();
            bos.close();
        }
        if (role==4||role==2){
            element=new SheKeChuEntity(1,uo.getFinishtime(),uo.getName(),uo.getPartment(), bos.toByteArray(),uo.getJietidengji(),uo.getXiangmujibie(),uo.getJianglijibie(),uo.getJianglidengji(),uo.getHuojiangleibie(),uo.getChengguotype(),uo.getDanwei(),uo.getShenbao(),uo.getShenbaotype());
        }else{
            element=new SheKeChuEntity(0,uo.getFinishtime(),uo.getName(),uo.getPartment(), bos.toByteArray(),uo.getJietidengji(),uo.getXiangmujibie(),uo.getJianglijibie(),uo.getJianglidengji(),uo.getHuojiangleibie(),uo.getChengguotype(),uo.getDanwei(),uo.getShenbao(),uo.getShenbaotype());
        }
        int ref=mapper.shenBaoSheKeChu(element);
        //添加参与人
        if(ref!=0){
            for (Integer badge:people) {
                newSystemMapper.insertNewSystemParticipation(new NewParticipationEntity(badge,element.getId(),uo.getShenbaotype()));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public List<Integer> getKeYanHuoJiangDetailBadge(Integer id,String type) {
        List<People> people=mapper.getKeYanHuoJiangDetail(id,type);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editSheKeChu(SheKeChuEditUO uo) {
        int ref=mapper.editSheKeChu(uo);
        Integer id=uo.getId();
        String type=uo.getType();
        Integer[] people=uo.getPeople();
        if(ref==1){
            mapper.deleteAllPeople(id,type);
            for (Integer p:people) {
                newSystemMapper.editNewSystemPeople(id,p,type);
            }
        }
        return ref;
    }
}
