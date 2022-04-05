package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ZhuZuoService")
public class ZhuZuoimpl implements ZhuZuoService {
    @Autowired
    private ZhuZuoMapper mapper;
    @Override
    public ResVO getAllZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getAllZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchZhuZuo(DTO dTO) {
        List<List<Object>>data=mapper.getSearchZhuZuo(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteZhuZuo(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneZhuZuo(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneZhuZuo(Integer id) {
        mapper.deleteOneZhuZuo(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getZhuZuoDetial(Integer id) {
        return new JsonBean(200,"",mapper.getZhuZuoDetail(id));
    }

    @Override
    public JsonBean insertZhuZuo(HeBingEntity entity,Integer[] people) {
        mapper.insertZhuZuo(entity);
        for (Integer ach_id:people) {
            mapper.insertZhuZuoParticipation(new ParticipationEntity(ach_id,entity.getId(),9));
        }
        return new JsonBean(200,"","");
    }
}
