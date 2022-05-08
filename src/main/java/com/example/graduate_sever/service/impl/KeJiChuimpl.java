package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.KeJiChuMapper;
import com.example.graduate_sever.Dao.NewSystemMapper;
import com.example.graduate_sever.Dao.SheKeChuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.KeJiChuEditUO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.UO.KeJiChuUO;
import com.example.graduate_sever.common.UO.NewSystemUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;
import com.example.graduate_sever.entity.NewParticipationEntity;
import com.example.graduate_sever.entity.NewSystemEntity;
import com.example.graduate_sever.entity.SheKeChuEntity;
import com.example.graduate_sever.model.SheKeChuModel;
import com.example.graduate_sever.service.KeJiChuService;
import com.example.graduate_sever.service.SheKeChuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("KeJiChuService")
public class KeJiChuimpl implements KeJiChuService {
    @Autowired
    private KeJiChuMapper mapper;
    @Autowired
    private NewSystemMapper newSystemMapper;
    @Autowired
    private SheKeChuMapper sheKeChuMapper;

    @Override
    public ResVO getAllKeJiChu(DTO dTO) {
        List<List<Object>>data=mapper.getAllKeJiChu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeJiChu(DTO dTO) {
        List<List<Object>>data=mapper.getSearchKeJiChu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeJiChu(List<Integer> ids) {
        String type;
        for (int id:ids) {
            type=mapper.kejichuType(id);
            mapper.deleteOneKeJiChu(id);
            sheKeChuMapper.deleteAllPeople(id,type);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeJiChu(Integer id, String type) {
        mapper.deleteOneKeJiChu(id);
        sheKeChuMapper.deleteAllPeople(id,type);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeJiChuDetail(Integer id, String type) {
        return new JsonBean(200,"",mapper.getKeJiChuDetail(id,type));
    }

    @Override
    public JsonBean insertKeJiChu(KeJiChuUO uo) {
//        NewSystemEntity element;
//        Integer[] people=uo.getPeople();
//        Integer role=uo.getRole();
//        //读取证明材料
//        File file=new File(uo.getPath());
//        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
//        BufferedInputStream bin = null;
//        try {
//            bin = new BufferedInputStream(new FileInputStream(file));
//            byte[] buffer = new byte[1024];
//            while (bin.read(buffer) > 0) {
//                bos.write(buffer);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            bin.close();
//            bos.close();
//        }
//        if (role==4||role==2){
//            element=new NewSystemEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getShenbao(), bos.toByteArray(),uo.getType());
//        }else{
//            element=new NewSystemEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getShenbao(), bos.toByteArray(),uo.getType());
//        }
//        int ref=mapper.KeJiChu(element);
//        //添加参与人
//        if(ref!=0){
//            for (Integer badge:people) {
//                newSystemMapper.insertNewSystemParticipation(new NewParticipationEntity(badge,element.getId(),uo.getType()));
//            }
//        }
//        return new JsonBean(200,"",ref);
        return null;
    }

    @Override
    public List<Integer> getKeJiChuBadge(Integer id, String type) {
        List<People> people=mapper.getKeJiChuDetail(id,type);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editKeJiChu(KeJiChuEditUO uo) {
        int ref=mapper.editKeJiChu(uo);
        Integer id=uo.getId();
        String type=uo.getType();
        Integer[] people=uo.getPeople();
        if(ref==1){
            sheKeChuMapper.deleteAllPeople(id,type);
            for (Integer p:people) {
                newSystemMapper.editNewSystemPeople(id,p,type);
            }
        }
        return ref;
    }

    @Override
    public Metails getKeJiChuMetails(Integer id) {
        return mapper.getKeJiChuMetails(id);
    }

    @Override
    public int passKeJiChu(Integer id, Integer pass) {
        return mapper.passKeJiChu(id,pass);
    }
}
