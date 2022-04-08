package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.XueKeJingSaiMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.XueKeJingSaiService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("XueKeJingSaiService")
public class XueKeJingSaiimpl implements XueKeJingSaiService {
    @Autowired
    private XueKeJingSaiMapper mapper;
    @Override
    public ResVO getAllJingSai(DTO dTO) {
        List<List<Object>>data=mapper.getAllJingSai(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJingSai(DTO dTO) {
        List<List<Object>>data=mapper.getSearchJingSai(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJingSai(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneJingSai(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJingSai(Integer id) {
        mapper.deleteOneJingSai(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJingSaiDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJingSaiDetail(id));
    }

    @Override
    public JsonBean insertJingSai(CompetitionEntity entity,Integer[] people) {
        mapper.insertJingSai(entity);
        for (Integer ach_id:people) {
            mapper.insertJingSaiParticipation(new ParticipationEntity(ach_id,entity.getId(),13));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean shenBaoJingSai(CompetitionEntity entity, Integer[] people) {
        int ref=mapper.shenBaoJingSai(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertJingSaiParticipation(new ParticipationEntity(ach_id,entity.getId(),13));
            }
        }
        return new JsonBean(200,"",ref);
    }
}
