package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.RuanJianZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.RuanJianZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
